package br.com.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Messages;

import br.com.email.Email;
import br.com.model.Cliente;
import br.com.model.Comanda;
import br.com.model.ItemComanda;
import br.com.model.Profissional;
import br.com.model.Servico;
import br.com.model.StatusComandaEnum;
import br.com.model.StatusItemEnum;
import br.com.model.TipoFormaPagamentoEnum;
import br.com.model.ViewComanda;
import br.com.model.ViewComandaVO;
import br.com.reports.GeraRelatorioViewComanda;
import br.com.service.ClienteService;
import br.com.service.ProfissionalService;
import br.com.service.ComandaService;
import br.com.service.ItemComandaService;
import br.com.service.ServicoService;
import br.com.service.ViewComandaService;

/**
 * @author carledwin
 */
@Named
@ViewScoped
public class ComandaBean extends AbstractBean<Comanda> implements Serializable {

	private static final String RELATORIO_CONSULTA_SERVICOS_COMANDAS = "/br/com/reports/reportVComandas2.jrxml";

	private static final long serialVersionUID = 1L;

	private Long codigoObjeto;
	private String nomeCliente;
	private String nomeColaborador;
	private Calendar dataInicial;
	private Calendar dataFinal;
	private List<Comanda> todos;
	private List<Servico> todosServicos;
	private List<Cliente> todosClientes;
	private List<Profissional> todosProfissionais;
	private Cliente cliente;
	private List<Cliente> clientes = new ArrayList<>();
	private List<Servico> servicos = new ArrayList<>();
	private List<Profissional> colaboradores = new ArrayList<>();
	private List<Profissional> colaboradores2 = new ArrayList<>();
	private List<ViewComanda> todosViewComanda;
	private Long reportIdComanda;
	private Long reportIdCliente;
	private Calendar reportComandaDataCriacao;
	private Calendar reportComandaDataAlteracao;
	private Long reportIdColaborador1;
	private Long reportIdColaborador2;
	private TipoFormaPagamentoEnum tiposPagamento;
	@Inject
	Comanda objetoPrincipal;
	@Inject
	Comanda objetoSelecionado;
	@Inject
	private ItemComanda novoItemComanda;
	@Inject
	ComandaService objetoService;
	@Inject
	ItemComandaService itemComandaService;
	@Inject
	ProfissionalService colaboradorService;
	@Inject
	ViewComandaService viewComandaService;
	@Inject
	ServicoService servicoService;
	@Inject
	private Cliente clienteSelecionado;
	@Inject
	private Servico servicoSelecionado;
	@Inject
	private Profissional colaboradorSelecionado1;
	@Inject
	private Profissional colaboradorSelecionado2;
	@Inject
	ClienteService clienteService;

	@Inject
	private ItemComanda novoItem;
	@Inject
	private Profissional colaborador1;
	@Inject
	private Profissional colaborador2;

	private List<ItemComanda> listaServicosRealizados;

	public ComandaBean() {

	}

	@Override
	public void reiniciar() {
		objetoPrincipal = new Comanda();
		Cliente cliente = new Cliente();
		objetoPrincipal.setCliente(cliente);
		objetoPrincipal.setDataCriacao(Calendar.getInstance());
		todos = null;
		codigoObjeto = new Long(0l);
		objetoSelecionado = null;

		setCadastro(false);
		setPesquisa(false);
		setAlteracao(false);
		setConsulta(false);
		setRelatorio(false);
		setConsultaView(false);
		listaServicosRealizados = new ArrayList<>();
		objetoPrincipal.setItemComandas(listaServicosRealizados);
		servicoSelecionado = new Servico();
		colaboradorSelecionado1 = new Profissional();
		colaboradorSelecionado2 = new Profissional();
		clienteSelecionado = new Cliente();
		novoItemComanda = new ItemComanda();
		reiniciarPesquisaRelatorio();

	}

	@Override
	public void prepararRelatorio() {
		pesquisarItemsParaGerarRelatorio();
		limpar();
		reiniciar();
		reiniciarPesquisaRelatorio();
		setRelatorio(true);
	}

	@Override
	public void prepararAlterar() {

		setPesquisa(false);
		setConsulta(false);

		objetoPrincipal = objetoSelecionado;
		listaServicosRealizados = objetoPrincipal.getItemComandas();
		clienteSelecionado = objetoPrincipal.getCliente();
		calculaTotalValorItemsComanda();

		setAlteracao(true);
		setPesquisa(false);
		setCadastro(false);
		setPesquisa(false);
		setConsulta(false);
	}

	@Override
	public void alterar() {
		objetoService.save(objetoPrincipal);
		limpar();
		reiniciar();
	}

	@Override
	public void cancelar() {
		reiniciar();
	}

	@Override
	public void cadastrar() {

		if (listaServicosRealizados != null && !listaServicosRealizados.isEmpty()) {

			objetoPrincipal.setItemComandas(listaServicosRealizados);
			objetoPrincipal.setCliente(clienteSelecionado);
			objetoPrincipal.setUsuarioAlteracao(UsuarioBean.getUsuarioSessao());

			if (objetoPrincipal.getId() == null) {
				objetoPrincipal.setStatus(StatusComandaEnum.EMA);
				Messages.addInfo(null, "Registro incluido com sucesso.");
			} else {
				Messages.addInfo(null, "Registro alterado com sucesso.");
				objetoPrincipal.setDataAlteracao(Calendar.getInstance());
			}

			objetoPrincipal = objetoService.save(objetoPrincipal);

			for (ItemComanda item : listaServicosRealizados) {
				item.setComanda(objetoPrincipal);
				itemComandaService.save(item);
			}

			limpar();
			reiniciar();
		} else {
			Messages.addError(null, "Nenhum servio foi adicionado  comanda.");
		}
	}

	@Override
	public void pesquisar() {
		todos = objetoService.findByParam(codigoObjeto, nomeCliente, nomeColaborador, dataInicial, dataFinal);
		setConsulta(true);
	}

	@Override
	public void gerarRelatorio() {
		pesquisarItemsParaGerarRelatorio();
		setConsultaView(true);
		gerarRelatorioViewComandas(true, false);
	}

	@Override
	public void excluir() {
		objetoService.remove(objetoSelecionado);
		Messages.addWarn(null, "Registro excluido com sucesso.");
		if (isPesquisa() && codigoObjeto != null) {
			reiniciar();
			setPesquisa(true);
			todos = null;
		} else {
			Long excluido = objetoSelecionado.getId();
			reiniciar();
			todos = objetoService.findByParam(excluido, nomeCliente, nomeColaborador, dataInicial, dataFinal);
			if (getTodos().size() == 0) {
				todos = null;
			}
		}
		setConsulta(true);
	}

	@Override
	public void reiniciarPesquisaRelatorio() {
		todosViewComanda = new ArrayList<ViewComanda>();
		reportIdComanda = new Long(0l);
		reportIdCliente = new Long(0l);
		reportComandaDataCriacao = Calendar.getInstance();
		reportComandaDataAlteracao = Calendar.getInstance();
		reportIdColaborador1 = new Long(0l);
		reportIdColaborador2 = new Long(0l);
	}

	@Override
	public List<Comanda> getTodos() {
		if (todos == null) {
			todos = objetoService.listAll();
		}
		return todos;
	}

	public List<Cliente> completaBuscaCliente(String nomeCliente) {
		this.clientes = clienteService.findByNome(nomeCliente);

		List<Cliente> sugestoesClientes = new ArrayList<>();

		for (Cliente cliente : this.clientes) {
			if (cliente.getNome().startsWith(nomeCliente))
				sugestoesClientes.add(cliente);
		}
		return sugestoesClientes;
	}

	public List<Servico> completaBuscaServico(String nomeServico) {
		this.servicos = servicoService.findByNome(nomeServico);

		List<Servico> sugestoesServicos = new ArrayList<>();
		for (Servico servico : this.servicos) {
			if (servico.getDescricao().startsWith(nomeServico))
				sugestoesServicos.add(servico);
		}
		return sugestoesServicos;
	}

	public List<Profissional> completaBuscaColaborador(String nomeColaborador) {
		this.setColaboradores(colaboradorService.findByNome(nomeColaborador));

		List<Profissional> sugestoesColaboradores = new ArrayList<>();
		for (Profissional colaborador : this.colaboradores) {
			if (colaborador.getNome().startsWith(nomeColaborador))
				sugestoesColaboradores.add(colaborador);
		}
		return sugestoesColaboradores;
	}

	public List<Profissional> completaBuscaColaborador2(String nomeColaborador2) {
		this.setColaboradores2(colaboradorService.findByNome(nomeColaborador2));

		List<Profissional> sugestoesColaboradores2 = new ArrayList<>();
		for (Profissional colaborador : this.colaboradores2) {
			if (colaborador.getNome().startsWith(nomeColaborador2))
				sugestoesColaboradores2.addAll(colaboradores2);
		}
		return sugestoesColaboradores2;
	}

	public void adicionarServicoRealizado() {
		objetoPrincipal.getItemComandas().add(novoItemComanda);
		calculaTotalValorItemsComanda();

		Messages.addInfo(null, "Item incluidoa comanda com sucesso!");
	}

	public void prepareNovoItem() {
		novoItemComanda = new ItemComanda();
		novoItemComanda.setProfissional(new Profissional());
		Servico s = new Servico();
		s.setValor(new BigDecimal("0.00"));
		novoItemComanda.setServico(s);
		novoItemComanda.setStatuItem(StatusItemEnum.A_CALCULAR);
		novoItemComanda.setComanda(objetoPrincipal);
	}

	private void calculaTotalValorItemsComanda() {
		objetoPrincipal.setTotal(new BigDecimal("0.00"));

		for (ItemComanda item : objetoPrincipal.getItemComandas()) {
			BigDecimal total = objetoPrincipal.getTotal().add(item.getServico().getValor());
			objetoPrincipal.setTotal(total);
		}
	}

	public void enviarRelatorioPorEmail() {
		pesquisarItemsParaGerarRelatorio();
		setConsultaView(true);
		gerarRelatorioViewComandas(false, true);
	}

	private void gerarRelatorioViewComandas(boolean visualizar, boolean enviarPorEmail) {
		GeraRelatorioViewComanda g = new GeraRelatorioViewComanda();

		List<ViewComandaVO> listaVO = ViewComandaVO.parseViewComandaForVO(todosViewComanda);

		String salvarLocal = "C:/consultaServicosdeComandas.pdf";

		Email email = new Email();
		email.setAssunto("Relatrio Servios de Comandas");
		email.setMensagem("Relatrio gerado com sucesso. Segue em anexo.");
		email.setTo("carlinstr@gmail.com");
		g.geraRelatorioViewComanda(listaVO, RELATORIO_CONSULTA_SERVICOS_COMANDAS, visualizar, enviarPorEmail,
				salvarLocal, email);
	}

	public void doDownloadRotuloORM(ActionEvent event) throws IOException {

		byte[] fileByteArray = null;// pegar os bytes

		FacesContext fx = generatePdfFromByte("nomeDoAquivo", fileByteArray, "application/pdf");
		fx.responseComplete();

	}

	private FacesContext generatePdfFromByte(String fileName, byte[] fileByteArray, String contentType)
			throws IOException {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment; filename=\" " + fileName + "\"");

		OutputStream output = response.getOutputStream();
		output.write(fileByteArray);
		output.close();
		return facesContext;
	}

	private void pesquisarItemsParaGerarRelatorio() {
		todosViewComanda = viewComandaService.findByParam(reportIdComanda, reportIdCliente, reportIdColaborador1,
				reportComandaDataCriacao, reportComandaDataAlteracao);
	}

	public void excluirItem(ItemComanda item) {
		objetoPrincipal.getItemComandas().remove(item);
		item.setComanda(null);

		BigDecimal total = objetoPrincipal.getTotal().subtract(item.getServico().getValor());
		objetoPrincipal.setTotal(total);

		if (item.getId() != null) {
			objetoService.save(objetoPrincipal);
			calculaTotalValorItemsComanda();
			todos = null;
		}
		Messages.addWarn(null, "Item removido com sucesso.");
	}

	public void pesquisarCliente() {

	}

	public void emitir() {
		Messages.addInfo(null, "Relatrio gerado com sucesso.");
	}

	public void removerServicoRealizado() {
		// listaServicosRealizados.remove(0);
	}

	public void alterarServicoRealizado() {
		// listaServicosRealizados.set(0, novoServico);
	}

	public void limparListaServicosRealizados() {
		// listaServicosRealizados = new ArrayList<>();
	}

	public void prepararConsultaServicos() {

	}

	public TipoFormaPagamentoEnum[] getTiposPagamento() {
		return tiposPagamento.values();
	}

	public List<Servico> getTodosServicos() {
		if (todosServicos == null) {
			todosServicos = servicoService.listAll();
		}
		return todosServicos;
	}

	public List<Profissional> getTodosProfissionais() {
		if (todosProfissionais == null) {
			todosProfissionais = colaboradorService.listAll();
		}
		return todosProfissionais;
	}

	public void setTodosProfissionais(List<Profissional> todosProfissionais) {
		this.todosProfissionais = todosProfissionais;
	}

	public List<Cliente> getTodosClientes() {
		if (todosClientes == null) {
			todosClientes = clienteService.listAll();
		}
		return todosClientes;
	}

	public Long getCodigoObjeto() {
		return codigoObjeto;
	}

	public void setCodigoObjeto(Long codigoObjeto) {
		this.codigoObjeto = codigoObjeto;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}

	public Calendar getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<Profissional> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Profissional> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public List<Profissional> getColaboradores2() {
		return colaboradores2;
	}

	public void setColaboradores2(List<Profissional> colaboradores2) {
		this.colaboradores2 = colaboradores2;
	}

	public List<ViewComanda> getTodosViewComanda() {
		return todosViewComanda;
	}

	public void setTodosViewComanda(List<ViewComanda> todosViewComanda) {
		this.todosViewComanda = todosViewComanda;
	}

	public Long getReportIdComanda() {
		return reportIdComanda;
	}

	public void setReportIdComanda(Long reportIdComanda) {
		this.reportIdComanda = reportIdComanda;
	}

	public Long getReportIdCliente() {
		return reportIdCliente;
	}

	public void setReportIdCliente(Long reportIdCliente) {
		this.reportIdCliente = reportIdCliente;
	}

	public Calendar getReportComandaDataCriacao() {
		return reportComandaDataCriacao;
	}

	public void setReportComandaDataCriacao(Calendar reportComandaDataCriacao) {
		this.reportComandaDataCriacao = reportComandaDataCriacao;
	}

	public Calendar getReportComandaDataAlteracao() {
		return reportComandaDataAlteracao;
	}

	public void setReportComandaDataAlteracao(Calendar reportComandaDataAlteracao) {
		this.reportComandaDataAlteracao = reportComandaDataAlteracao;
	}

	public Long getReportIdColaborador1() {
		return reportIdColaborador1;
	}

	public void setReportIdColaborador1(Long reportIdColaborador1) {
		this.reportIdColaborador1 = reportIdColaborador1;
	}

	public Long getReportIdColaborador2() {
		return reportIdColaborador2;
	}

	public void setReportIdColaborador2(Long reportIdColaborador2) {
		this.reportIdColaborador2 = reportIdColaborador2;
	}

	public ItemComanda getNovoItemComanda() {
		return novoItemComanda;
	}

	public void setNovoItemComanda(ItemComanda novoItemComanda) {
		this.novoItemComanda = novoItemComanda;
	}

	public ItemComandaService getItemComandaService() {
		return itemComandaService;
	}

	public void setItemComandaService(ItemComandaService itemComandaService) {
		this.itemComandaService = itemComandaService;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Servico getServicoSelecionado() {
		return servicoSelecionado;
	}

	public void setServicoSelecionado(Servico servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}

	public Profissional getColaboradorSelecionado1() {
		return colaboradorSelecionado1;
	}

	public void setColaboradorSelecionado1(Profissional colaboradorSelecionado1) {
		this.colaboradorSelecionado1 = colaboradorSelecionado1;
	}

	public Profissional getColaboradorSelecionado2() {
		return colaboradorSelecionado2;
	}

	public void setColaboradorSelecionado2(Profissional colaboradorSelecionado2) {
		this.colaboradorSelecionado2 = colaboradorSelecionado2;
	}

	public ItemComanda getNovoItem() {
		return novoItem;
	}

	public void setNovoItem(ItemComanda novoItem) {
		this.novoItem = novoItem;
	}

	public Profissional getColaborador1() {
		return colaborador1;
	}

	public void setColaborador1(Profissional colaborador1) {
		this.colaborador1 = colaborador1;
	}

	public Profissional getColaborador2() {
		return colaborador2;
	}

	public void setColaborador2(Profissional colaborador2) {
		this.colaborador2 = colaborador2;
	}

	public List<ItemComanda> getListaServicosRealizados() {
		return listaServicosRealizados;
	}

	public void setListaServicosRealizados(List<ItemComanda> listaServicosRealizados) {
		this.listaServicosRealizados = listaServicosRealizados;
	}

	public void setTodos(List<Comanda> todos) {
		this.todos = todos;
	}

	public void setTodosServicos(List<Servico> todosServicos) {
		this.todosServicos = todosServicos;
	}

	public void setTodosClientes(List<Cliente> todosClientes) {
		this.todosClientes = todosClientes;
	}

	public void setTiposPagamento(TipoFormaPagamentoEnum tiposPagamento) {
		this.tiposPagamento = tiposPagamento;
	}

	public Comanda getObjetoPrincipal() {
		return objetoPrincipal;
	}

	public void setObjetoPrincipal(Comanda objetoPrincipal) {
		this.objetoPrincipal = objetoPrincipal;
	}

	public Comanda getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Comanda objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

}
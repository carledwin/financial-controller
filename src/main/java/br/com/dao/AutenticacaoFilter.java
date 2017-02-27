package br.com.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.model.Usuario;


//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



/**
 * Classe mais importante, nela que ser feita a 
 * verificao de login, senha e perfil a qual o 
 * usurio pertence
 * @author ceanascimento
 *
 */
@SessionScoped
public class AutenticacaoFilter extends UsernamePasswordAuthenticationFilter{
	
	/*private EntityManagerFactory factory = Persistence.createEntityManagerFactory("appCDIUnit");
	private EntityManager em;*/
	
	@PersistenceContext(unitName = "BeautySalonManagement-MySql-PU")
	private EntityManager em;
	private String mensagem;
	
	private Usuario usuario;
	
	
	/**
	 * Verifica o usuario
	 * return os dados do usurio
	 */
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
		String login = request.getParameter("j_login");
		String senha = request.getParameter("j_senha");

		
		try {
			Usuario usuario = buscarUsuario(login, senha);
			
			if(usuario != null){
	
					
					Collection<GrantedAuthority> regras = new ArrayList<GrantedAuthority>();
					regras.add(new SimpleGrantedAuthority(usuario.getPerfil().getRegra()));
					
					//joga o usuario na sessao da aplicacao para recuperar quando necessario
					request.getSession().setAttribute("usuarioLogado", usuario);
					
					//redireciona o usuario para a pagina inicial, sera recuperada pela class AutenticacaoPhaseListener
					mensagem = "Bem vindo: " + usuario.getLogin();
					
					//return com os dados do usuario
					return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha(), regras);
			
			}else{
				
				//redireciona o usurio para a pgina inicial
				mensagem = "Dados incorretos.";
				throw new BadCredentialsException(mensagem);
			}
		} catch (Exception e) {
			throw new BadCredentialsException("Erro ao tentar efetuar o login: -->  " + e.getMessage());
		}
	}
	
	
	public Usuario buscarUsuario(String login, String senha){
		
		usuario = null;
		
		
		/*em = factory.createEntityManager();*/
		
		try {
			Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha ");
			
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("DAO: Nao foi encontrado resultado!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			em.close();
		}
		
		return usuario;
		
	}
	
	
	/**
	 * 
	 * Sucesso no login
	 * Ocorrer return UsernamePasswordAuthenticationToken
	 * 
	 */
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException{
		
		//informa ao Spring Security que existe uma autenticacao
		SecurityContextHolder.getContext().setAuthentication(authResult);
		
		mensagem = "Bem vindo " + usuario.getLogin() +"!";
		//disponibiliza a mensagem na sessao da plicacao
		request.getSession().setAttribute("msg", mensagem);
		
		//redireciona o usuario para a pagina inicial
		response.sendRedirect("acesso_restrito/index_acesso_restrito.jsf");
		
	}
	
	
	/**
	 * Erro no login
	 * No ocorrer return diferente de (Erro) UsernamePasswordAuthenticationToken
	 */
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException{

		mensagem = "Login ou Senha invlido.";
		//disponibiliza a mensagem na sessao da plicacao
		request.getSession().setAttribute("msg", mensagem);
		
		//redireciona o usuario para a pagina de login
		response.sendRedirect("login.jsf");
	}
	

}

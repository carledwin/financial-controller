/* Table node "vw_consulta_servicos_comanda" not found in tree. */
ALTER VIEW vw_comanda AS 
	SELECT 
		CM.ID,
		CM.data_criacao CM_DT_CR,
		CM.total CM_TT,
		CM.status CM_ST,
    	IT_CM.ID IT_CM_ID,
        CL.ID_CLIENTE CL_ID,
        PS3.NOME CL_NM,
        IT_CM.desconto IT_DESCO_SV,
        IT_CM.colaborador1_ID_COLABORADOR CO_1_ID,
        PS1.NOME CO_1_NM,
        IT_CM.colaborador2_ID_COLABORADOR CO_2_ID,
        PS2.NOME CO_2_NM,
        SV.ID SV_ID, 
		SV.DESCRICAO SV_DS,
		SV.VALOR SV_VL,
        FP.ID FP_ID, 
        FP.TIPO FP_VL_TP,
        FP.valorInicial FP_VL_IN,
        FP.valorFinal FP_VL_FN
		FROM tb_item_comanda IT_CM
		JOIN tb_comanda CM 	ON CM.ID =  IT_CM.COMANDA_ID
		LEFT JOIN tb_pessoa PS1 	ON PS1.IDENT = IT_CM.colaborador1_ID_COLABORADOR
        LEFT JOIN tb_pessoa  PS2 	ON PS2.IDENT = IT_CM.colaborador2_ID_COLABORADOR
		JOIN tb_pessoa PS3 	ON PS3.IDENT = CM.CLIENTE_ID
        JOIN tb_cliente CL		ON CL.ID_CLIENTE = PS3.IDENT	
		JOIN tb_servico SV 			ON IT_CM.SERVICO_ID = SV.ID
		JOIN tb_forma_pagamento FP  ON CM.FORMA_PAGAMENTO_ID  = FP.ID
	
	;
 
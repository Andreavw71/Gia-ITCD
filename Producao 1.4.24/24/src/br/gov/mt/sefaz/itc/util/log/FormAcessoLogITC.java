package br.gov.mt.sefaz.itc.util.log;

import br.com.abaco.util.EntidadeVo;
import br.com.abaco.util.log.FormAcessoLog;
import br.com.abaco.util.log.FormLogBean;

import br.gov.mt.sefaz.itc.util.dominio.DomnCodigoTransacao;
import br.gov.mt.sefaz.itc.util.dominio.DomnNumeroParticao;

import java.util.HashMap;


/**
 * Classe que define o mapeamento de código transação e número partição para todas as funcionalidades
 * do sistema ITCD utilizada para construção do LOG.
 * @author Karen Barbato
 * @version $Revision: 1.2 $
 */
public class FormAcessoLogITC extends FormAcessoLog implements FuncionalidadeLog
{
	/**
	 * Através do nome da funcionalidade, as informações referente ao log serão capturadas e 
	 * serão adicionados ao VO correspondente.
	 * @param chave - representa o nome da funcionalidade
	 * @param entidadeVo - o vo que está sendo trabalhado.
	 * @implemented by Karen Barbato
	 */
	public FormAcessoLogITC(String chave, EntidadeVo entidadeVo)
	{
		super(entidadeVo);
		synchronized (FormAcessoLog.class)
		{
			mapearLog();	
		}
		dadosLog(chave, entidadeVo);
	}

	protected void mapearLog()
	{
		if(listaDados.isEmpty() )
		{
			listaDados = new HashMap();
			metodoBens();
			metodoCultura();
			metodoRebanho();
			metodoNaturezaOperacao();
			metodoConfiguracaoGerencialParametros();
			metodoBenfeitoria();
			metodoConstrucao();
			metodoMultaMora();
			metodoParametroLegislacao();
			metodoAjuda();
			metodoGia();
		   metodoAvaliacao();
		   medotoGIADAR();
		}
	}

	/**
	 * Método que define o código transação e número partição para as funcionalidades da funcionalidade BEM.
	 * @implemented by Karen Barbato
	 */
	private void metodoBens()
	{
		listaDados.put(INCLUIR_BEM_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.BEM_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(ALTERAR_BEM_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.BEM_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));		
	}
	
	/**
	 * Método que define o código transação e número partição para as funcionalidades da funcionalidade CULTURA.
	 * @implemented by Karen Barbato
	 */
	private void metodoCultura()
	{
		listaDados.put(INCLUIR_CULTURA_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.CULTURA_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(ALTERAR_CULTURA_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.CULTURA_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));		
	}	
	
	/**
	 * Método que define o código transação e número partição para as funcionalidades da funcionalidade REBANHO.
	 * @implemented by Karen Barbato
	 */
	private void metodoRebanho()
	{
		listaDados.put(INCLUIR_REBANHO_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.REBANHO_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(ALTERAR_REBANHO_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.REBANHO_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));		
	}		
	
	private void metodoNaturezaOperacao() 
	{
		listaDados.put(INCLUIR_NATUREZA_OPERACAO_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.NATUREZA_OPERACAO_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(ALTERAR_NATUREZA_OPERACAO_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.NATUREZA_OPERACAO_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));
	}
	
	private void metodoConfiguracaoGerencialParametros() 
	{
		listaDados.put(INCLUIR_CONFIGURACAO_GERENCIAL_PARAMETRO_PROPRIATRANSACAO, new FormLogBean(DomnCodigoTransacao.CONFIGURACAO_GERENCIAL_PARAMETROS_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(ALTERAR_CONFIGURACAO_GERENCIAL_PARAMETRO_PROPRIATRANSACAO, new FormLogBean(DomnCodigoTransacao.CONFIGURACAO_GERENCIAL_PARAMETROS_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));
	}
	
	private void metodoBenfeitoria() 
	{
		listaDados.put(INCLUIR_BENFEITORIA_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.BENFEITORIA_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(ALTERAR_BENFEITORIA_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.BENFEITORIA_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));
	}
	
	private void metodoConstrucao() 
	{
		listaDados.put(INCLUIR_CONSTRUCAO_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.CONSTRUCAO_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(ALTERAR_CONSTRUCAO_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.CONSTRUCAO_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));
	}
	
	private void metodoMultaMora() 
	{
		listaDados.put(INCLUIR_MULTA_MORA_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.MULTA_MORA_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(ALTERAR_MULTA_MORA_PROPRIA_TRANSACAO, new FormLogBean(DomnCodigoTransacao.MULTA_MORA_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));
	}
	
	private void metodoParametroLegislacao()
	{
		listaDados.put(INCLUIR_PARAMETRO_LEGISLACAO_PROPRIA_TRANSACAO,  new FormLogBean(DomnCodigoTransacao.PARAMETRO_LEGISLACAO_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(ALTERAR_PARAMETRO_LEGISLACAO_PROPRIA_TRANSACAO,  new FormLogBean(DomnCodigoTransacao.PARAMETRO_LEGISLACAO_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(INCLUIR_PARAMETRO_LEGISLACAO_INCLUIR_ALIQUOTA,  new FormLogBean(DomnCodigoTransacao.PARAMETRO_LEGISLACAO_INCLUIR_ALIQUOTA, DomnCodigoTransacao.PARAMETRO_LEGISLACAO_INCLUIR));
		listaDados.put(ALTERAR_PARAMETRO_LEGISLACAO_EXCLUIR_ALIQUOTA,  new FormLogBean(DomnCodigoTransacao.PARAMETRO_LEGISLACAO_EXCLUIR_ALIQUOTA, DomnCodigoTransacao.PARAMETRO_LEGISLACAO_ALTERAR));
		listaDados.put(INCLUIR_PARAMETRO_LEGISLACAO_INCLUIR_MULTA,  new FormLogBean(DomnCodigoTransacao.PARAMETRO_LEGISLACAO_INCLUIR_MULTA, DomnCodigoTransacao.PARAMETRO_LEGISLACAO_INCLUIR));
		listaDados.put(ALTERAR_PARAMETRO_LEGISLACAO_EXCLUIR_MULTA,  new FormLogBean(DomnCodigoTransacao.PARAMETRO_LEGISLACAO_EXCLUIR_MULTA, DomnCodigoTransacao.PARAMETRO_LEGISLACAO_ALTERAR));
		listaDados.put(ALTERAR_PARAMETRO_LEGISLACAO_ALTERAR_MULTA,  new FormLogBean(DomnCodigoTransacao.PARAMETRO_LEGISLACAO_ALTERAR_MULTA, DomnCodigoTransacao.PARAMETRO_LEGISLACAO_ALTERAR));
		listaDados.put(ALTERAR_PARAMETRO_LEGISLACAO_ALTERAR_ALIQUOTA,  new FormLogBean(DomnCodigoTransacao.PARAMETRO_LEGISLACAO_ALTERAR_ALIQUOTA, DomnCodigoTransacao.PARAMETRO_LEGISLACAO_ALTERAR));
		listaDados.put(ALTERAR_PARAMETRO_LEGISLACAO_INCLUIR_ALIQUOTA,  new FormLogBean(DomnCodigoTransacao.PARAMETRO_LEGISLACAO_INCLUIR_ALIQUOTA, DomnCodigoTransacao.PARAMETRO_LEGISLACAO_ALTERAR));
		listaDados.put(ALTERAR_PARAMETRO_LEGISLACAO_INCLUIR_MULTA,  new FormLogBean(DomnCodigoTransacao.PARAMETRO_LEGISLACAO_INCLUIR_ALIQUOTA, DomnCodigoTransacao.PARAMETRO_LEGISLACAO_ALTERAR));		
	}
	
	private void metodoAjuda()
	{
		listaDados.put(INCLUIR_TELA_FUNCIONALIDADE_PROPRIA_TRANSACAO,  new FormLogBean(DomnCodigoTransacao.TELA_CAMPO_AJUDA_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(INCLUIR_TELA_CAMPO_AJUDA,  new FormLogBean(DomnCodigoTransacao.TELA_CAMPO_AJUDA_INCLUIR,  DomnCodigoTransacao.TELA_CAMPO_AJUDA_INCLUIR));		
		listaDados.put(ALTERAR_TELA_CAMPO_AJUDA,  new FormLogBean(DomnCodigoTransacao.TELA_CAMPO_AJUDA_ALTERAR, DomnCodigoTransacao.TELA_CAMPO_AJUDA_ALTERAR));
		listaDados.put(ALTERAR_TELA_FUNCIONALIDADE_PROPRIA_TRANSACAO,  new FormLogBean(DomnCodigoTransacao.TELA_CAMPO_AJUDA_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));
	}
	
	private void metodoGia() 
	{
		// temporario
		listaDados.put(INCLUIR_GIAITCD_TEMPORARIO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_TEMPORARIO_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(ALTERAR_GIAITCD_TEMPORARIO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_TEMPORARIO_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(DELETAR_GIAITCD_TEMPORARIO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_TEMPORARIO_DELETAR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		// gia
		listaDados.put(INCLUIR_GIAITCD, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(ALTERAR_GIAITCD, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		// bem tributavel
		listaDados.put(INCLUIR_BEM_TRIBUTAVEL, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_INCLUIR, DomnCodigoTransacao.GIA_ITCD_INCLUIR));
		listaDados.put(ALTERAR_BEM_TRIBUTAVEL, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_ALTERAR, DomnCodigoTransacao.GIA_ITCD_ALTERAR));
		{ // ficha rural
			listaDados.put(INCLUIR_BEM_TRIBUTAVEL_FICHA_IMOVEL_RURAL_GIAITCD, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_INCLUIR, DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_INCLUIR));
			listaDados.put(INCLUIR_BEM_TRIBUTAVEL_FICHA_IMOVEL_RURAL_CONSTRUCAO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_CONSTRUCAO_INCLUIR, DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_INCLUIR));
			listaDados.put(INCLUIR_BEM_TRIBUTAVEL_FICHA_IMOVEL_RURAL_BENFEITORIA, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_BENFEITORIA_INCLUIR, DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_INCLUIR));
			listaDados.put(INCLUIR_BEM_TRIBUTAVEL_FICHA_IMOVEL_RURAL_CULTURA, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_CULTURA_INCLUIR, DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_INCLUIR));
			listaDados.put(INCLUIR_BEM_TRIBUTAVEL_FICHA_IMOVEL_RURAL_REBANHO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_REBANHO_INCLUIR, DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_INCLUIR));
		
			listaDados.put(ALTERAR_BEM_TRIBUTAVEL_FICHA_IMOVEL_RURAL_GIAITCD, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_ALTERAR, DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_ALTERAR));
			listaDados.put(ALTERAR_BEM_TRIBUTAVEL_FICHA_IMOVEL_RURAL_CONSTRUCAO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_CONSTRUCAO_ALTERAR, DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_ALTERAR));
			listaDados.put(ALTERAR_BEM_TRIBUTAVEL_FICHA_IMOVEL_RURAL_BENFEITORIA, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_BENFEITORIA_ALTERAR, DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_ALTERAR));
			listaDados.put(ALTERAR_BEM_TRIBUTAVEL_FICHA_IMOVEL_RURAL_CULTURA, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_CULTURA_ALTERAR, DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_ALTERAR));
			listaDados.put(ALTERAR_BEM_TRIBUTAVEL_FICHA_IMOVEL_RURAL_REBANHO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_REBANHO_ALTERAR, DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_RURAL_ALTERAR));
		}
		{// ficha urbana
			listaDados.put(INCLUIR_BEM_TRIBUTAVEL_FICHA_IMOVEL_URBANO_GIAITCD, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_URBANO_INCLUIR, DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_INCLUIR));
			listaDados.put(INCLUIR_BEM_TRIBUTAVEL_FICHA_IMOVEL_URBANO_BENFEITORIA, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_URBANO_BENFEITORIA_INCLUIR, DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_URBANO_INCLUIR));
			
			listaDados.put(ALTERAR_BEM_TRIBUTAVEL_FICHA_IMOVEL_URBANO_GIAITCD, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_URBANO_ALTERAR, DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_ALTERAR));
			listaDados.put(ALTERAR_BEM_TRIBUTAVEL_FICHA_IMOVEL_URBANO_BENFEITORIA, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_URBANO_BENFEITORIA_ALTERAR, DomnCodigoTransacao.GIA_ITCD_FICHA_IMOVEL_URBANO_ALTERAR));
		}
		{ // gia inventario
			listaDados.put(INCLUIR_GIAITCD_INVENTARIO_ARROLAMENTO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_INCLUIR, DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_INCLUIR));
			listaDados.put(INCLUIR_GIAITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_INCLUIR, DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_INCLUIR));
			listaDados.put(INCLUIR_GIAITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA_INCLUIR, DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_INCLUIR));
			
			listaDados.put(ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_ALTERAR, DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_ALTERAR));
			listaDados.put(ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALTERAR, DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_ALTERAR));
			listaDados.put(ALTERAR_GIAITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA_ALTERAR, DomnCodigoTransacao.GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALTERAR));
		}
		{ // gia doacao
			listaDados.put(INCLUIR_GIAITCD_DOACAO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_DOACAO_INCLUIR, DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_INCLUIR));
			listaDados.put(INCLUIR_GIAITCD_DOACAO_BENEFICIARIO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_DOACAO_BENEFICIARIO_INCLUIR, DomnCodigoTransacao.GIA_ITCD_DOACAO_INCLUIR));
			
			listaDados.put(ALTERAR_GIAITCD_DOACAO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_DOACAO_ALTERAR, DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_ALTERAR));
			listaDados.put(ALTERAR_GIAITCD_DOACAO_BENEFICIARIO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_DOACAO_BENEFICIARIO_ALTERAR, DomnCodigoTransacao.GIA_ITCD_DOACAO_ALTERAR));
		}
		{ // gia separacao
			listaDados.put(INCLUIR_GIAITCD_SEPARACAO_DIVORCIO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_SEPARACAO_INCLUIR, DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_INCLUIR));
			listaDados.put(INCLUIR_BEM_TRIBUTAVEL_CONJUGE, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_SEPARACAO_CONJUGE_INCLUIR, DomnCodigoTransacao.GIA_ITCD_SEPARACAO_INCLUIR));
			
			listaDados.put(ALTERAR_GIAITCD_SEPARACAO_DIVORCIO, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_SEPARACAO_ALTERAR, DomnCodigoTransacao.GIA_ITCD_BEM_TRIBUTAVEL_ALTERAR));
			listaDados.put(ALTERAR_BEM_TRIBUTAVEL_CONJUGE, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_SEPARACAO_CONJUGE_ALTERAR, DomnCodigoTransacao.GIA_ITCD_SEPARACAO_ALTERAR));
		}
		// status gia
		listaDados.put(INCLUIR_GIAITCD_STATUS, new FormLogBean(DomnCodigoTransacao.GIA_ITCD_STATUS_INCLUIR, DomnNumeroParticao.GIA_ITCD_INCLUIR));
	}
	
	private void metodoAvaliacao() 
	{
		listaDados.put(INCLUIR_AVALIACAO_BEM_TRIBUTAVEL, new FormLogBean(DomnCodigoTransacao.AVALIACAO_BEM_TRIBUTAVEL_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
		listaDados.put(INCLUIR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR, new FormLogBean(DomnCodigoTransacao.AVALIACAO_SERVIDOR_INCLUIR, DomnCodigoTransacao.AVALIACAO_BEM_TRIBUTAVEL_INCLUIR));
	   listaDados.put(ALTERAR_AVALIACAO_BEM_TRIBUTAVEL, new FormLogBean(DomnCodigoTransacao.AVALIACAO_BEM_TRIBUTAVEL_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));
	   listaDados.put(ALTERAR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR, new FormLogBean(DomnCodigoTransacao.AVALIACAO_SERVIDOR_ALTERAR, DomnCodigoTransacao.AVALIACAO_BEM_TRIBUTAVEL_ALTERAR));
		listaDados.put(DELETAR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR, new FormLogBean(DomnCodigoTransacao.AVALIACAO_SERVIDOR_DELETAR, DomnCodigoTransacao.AVALIACAO_BEM_TRIBUTAVEL_ALTERAR));
	}
	
	private void medotoGIADAR() 
	{
		listaDados.put(INCLUIR_GIAITCD_DAR,  new FormLogBean(DomnCodigoTransacao.GIA_DAR_INCLUIR, DomnNumeroParticao.PROPRIA_TRANSACAO));
	   listaDados.put(ALTERAR_GIAITCD_DAR,  new FormLogBean(DomnCodigoTransacao.GIA_DAR_ALTERAR, DomnNumeroParticao.PROPRIA_TRANSACAO));
	}
}

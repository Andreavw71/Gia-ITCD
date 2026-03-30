package br.gov.mt.sefaz.itc.util.integracao.arrecadacao;

import br.com.abaco.sefaz.integracao.arrecadacao.DarEmitidoIntegracaoVo;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.IntegracaoErro;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;

import java.sql.Connection;
import java.sql.SQLException;

import sefaz.mt.arrecadacao.integracao.IntegracaoArrecadacao;


/**
 * @author Roniselton Barreto Rodrigues Silva
 * @version $Revision: 1.12 $
 */
public class DocumentoArrecadacaoBe extends AbstractBe
{

	public DocumentoArrecadacaoBe() throws SQLException
	{
		super();
	}

	/**
	 * @param conexao
	 */
	public DocumentoArrecadacaoBe(Connection conexao)
	{
		super(conexao);
	}

	/**Método Responsável por consultar Dar Quitado pelo numero do DAR Sequencial.
	 * @param darQuitadoIntegracaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @return
	 * @implemented by Roniselton Barreto Rodrigues Silva
	 */
	public DarQuitadoIntegracaoVo obterDarQuitadoPorNumrDarSequencial(DarQuitadoIntegracaoVo darQuitadoIntegracaoVo) throws ObjetoObrigatorioException, 
			  IntegracaoException
	{
		final String NUMERO_DAR_E_OBRIGATORIO = " O Número do DAR é obrigatório.";
		Validador.validaObjeto(darQuitadoIntegracaoVo);
		try
		{
			if (darQuitadoIntegracaoVo.isConsultaParametrizada())
			{
				if (Validador.isNumericoValido(darQuitadoIntegracaoVo.getParametroConsulta().getNumrDar()))
				{
                  //TODO Não utiliza a conexão do ITCD
						IntegracaoArrecadacao integracao = new IntegracaoArrecadacao();
						DarQuitadoIntegracaoVo darQuitado = 
										 new DarQuitadoIntegracaoVo(  integracao.obterDarQuitadoPorNumrDar(  darQuitadoIntegracaoVo.getParametroConsulta().getNumrDar() ));					
						return darQuitado;
				
				}
				else
				{
					throw new ParametroObrigatorioException(NUMERO_DAR_E_OBRIGATORIO);
				}
			}
			else
			{
				throw new ParametroObrigatorioException(NUMERO_DAR_E_OBRIGATORIO);
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_ARRECADACAO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_ARRECADACAO, e);
		}
	}
   
   
   
   public DarEmitidoIntegracaoVo obterDarEmitidoPorNumrDarOuCodgUofSeq(DarEmitidoIntegracaoVo darEmitidoIntegracaoVo)
     throws ObjetoObrigatorioException, IntegracaoException
   {
     String NUMERO_DAR_E_OBRIGATORIO = "O Numero do Dar / Código UOF Sequencial do DAR é obrigatório.";
     Validador.validaObjeto(darEmitidoIntegracaoVo);
     try
     {
       if (darEmitidoIntegracaoVo.isConsultaParametrizada())
       {
         if (Validador.isNumericoValido(darEmitidoIntegracaoVo.getParametroConsulta().getNumrDarSeqc()))
         {
           IntegracaoArrecadacao integracao = new IntegracaoArrecadacao(conn);
           DarEmitidoIntegracaoVo darEmitido = new DarEmitidoIntegracaoVo(integracao.obterDarEmitidoPorNumrDarOuCodgUof(darEmitidoIntegracaoVo.getParametroConsulta().getNumrDarSeqc()));
           return darEmitido;
         }
         throw new ParametroObrigatorioException("O Numero do Dar / Código UOF Sequencial do DAR é obrigatório.");
       }
       throw new ParametroObrigatorioException("O Numero do Dar / Código UOF Sequencial do DAR é obrigatório.");
     }
     catch (Exception e)
     {
       throw IntegracaoException.getInstance(2, "Arrecadação", e);
     }
     catch (Error e)
     {
       throw IntegracaoErro.getInstance(2, "Arrecadação", e);
     }
   }

}

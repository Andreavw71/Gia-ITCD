package br.gov.mt.sefaz.itc.util.integracao.ipva;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.IntegracaoErro;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.ipva.integracao.IntegracaoIpva;
import br.gov.mt.sefaz.ipva.integracao.ValorVenalVo;
import br.gov.mt.sefaz.ipva.integracao.model.ValorVenalBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichabem.fichaveiculo.FichaVeiculoVo;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import sefaz.mt.util.SefazDataHora;


public class ValorVenalIntegracaoBe extends AbstractBe
{
	public ValorVenalIntegracaoBe() throws SQLException
	{
		super();
	}

	public ValorVenalIntegracaoBe(Connection conexao)
	{
		super(conexao);
	}

   public FichaVeiculoVo pesquisarValorVenalVeiculo(FichaVeiculoVo fichaveiculoVo) throws IntegracaoException, ParametroObrigatorioException
   {
      ValorVenalIntegracaoVo valorVenalIntegracaoVo = null;
      Vector vectorValoresVenal = null; 
      SefazDataHora dataAtual = new SefazDataHora();
      String NUMERO_RENAVAN_OBRIGATORIO = "O Numero da Renavan é obrigatório.";

      Validador.isObjetoValido(fichaveiculoVo);
      try
      {
         if (!Validador.isNumericoValido(fichaveiculoVo.getNumrRenavam()))
         {
            throw new ParametroObrigatorioException(NUMERO_RENAVAN_OBRIGATORIO);
         }       
        
         ValorVenalBe veiculoBe = new ValorVenalBe(conn);
         vectorValoresVenal = new Vector();
         vectorValoresVenal = veiculoBe.buscarValorVenalViaRenavam(fichaveiculoVo.getNumrRenavam());
       
         if (Validador.isObjetoValido(vectorValoresVenal))
         {
            Iterator ite = vectorValoresVenal.iterator();
            while (ite.hasNext())
            {
               ValorVenalVo valorVenalVoAtual = (ValorVenalVo) ite.next();
               valorVenalIntegracaoVo = new ValorVenalIntegracaoVo(valorVenalVoAtual);

               if ((String.valueOf(valorVenalVoAtual.getAnoReferencia()).equals(String.valueOf(dataAtual.getAno()))))
               {
                  fichaveiculoVo.setAnoFabricacao(valorVenalIntegracaoVo.getAnoFabricacao());
                  fichaveiculoVo.setAnoLicenciamento(valorVenalIntegracaoVo.getAnoReferencia());
                  fichaveiculoVo.setNomeProprietario(valorVenalIntegracaoVo.getNomeProprietario());
                  fichaveiculoVo.setCategoria(valorVenalIntegracaoVo.getCategoria());
                  fichaveiculoVo.setAnoReferencia(valorVenalIntegracaoVo.getAnoReferencia());
                  fichaveiculoVo.setValorVenal(valorVenalIntegracaoVo.getValrVeiculo());
                  fichaveiculoVo.setNumrPlaca(valorVenalIntegracaoVo.getPlaca());
                  break;
               }
            }
         }
         else
         {
            FichaVeiculoVo fv = new FichaVeiculoVo();
            fv.setAnoFabricacao(fichaveiculoVo.getAnoFabricacao());
            fv.setNumrRenavam(fichaveiculoVo.getNumrRenavam());
            fichaveiculoVo = null;
            fichaveiculoVo = fv;
         }
         return fichaveiculoVo;
      }

      catch (ParametroObrigatorioException e)
      {
         throw e;
      }
      catch (Exception e)
      {
         throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_IPVA, e);
      }
      catch (Error e)
      {
         throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_IPVA, e);
      }
      finally
      {
         if (this.conn != null)
         {
            this.close();
         }
      }
   }
   // TODO alteracao
   public FichaVeiculoVo pesquisarModeloVeiculo(FichaVeiculoVo fichaveiculoVo)      
   throws IntegracaoException, ParametroObrigatorioException
   {        
     
      Vector vectorModeloVeiculo = null;  
      CategoriaIntegracaoVo categoriaIntegracaoVo = null;
      ArrayList listaModelo = new ArrayList();
      String NUMERO_PLACA_OBRIGATORIO = "O codigo da Marca é obrigatório.";    

      Validador.isObjetoValido(fichaveiculoVo);

      if (!Validador.isNumericoValido(fichaveiculoVo.getMarcaVo().getCodMarca()))
      {
         throw new ParametroObrigatorioException(NUMERO_PLACA_OBRIGATORIO);
      }

      try
      {
         ValorVenalBe veiculoBe = new ValorVenalBe(conn);

         vectorModeloVeiculo = new Vector();
         vectorModeloVeiculo = veiculoBe.buscarModelo(fichaveiculoVo.getMarcaVo().getCodMarca());
         if (Validador.isCollectionValida(vectorModeloVeiculo))
         {
            Iterator ite = vectorModeloVeiculo.iterator();
            while (ite.hasNext())
            {
               ValorVenalVo valorVenalVo = (ValorVenalVo) ite.next();
               categoriaIntegracaoVo = new CategoriaIntegracaoVo();
               categoriaIntegracaoVo.setCodgCategoria(valorVenalVo.getCategoria().getCodgCategoria());
               categoriaIntegracaoVo.setDescCategoria(valorVenalVo.getCategoria().getDescCategoria());
               listaModelo.add(categoriaIntegracaoVo);
            }
         }
         else
         {
            throw new Exception(MensagemErro.ERRO_CATEGORIA_VEICULO);
         }
         fichaveiculoVo.getCategoria().setCollVO(listaModelo);         
                  
         return fichaveiculoVo;      
      }
      catch (Exception e)
      {
         throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_IPVA, e);
      }
      catch (Error e)
      {
         throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_IPVA, e);
      }
      finally
      {
         if (this.conn != null)
         {
            this.close();
         }
      }
   }
   
   public MarcaIntegracaoVo pesquisarMarcaVeiculo()      
   throws IntegracaoException, ParametroObrigatorioException
   {
      MarcaIntegracaoVo marca = null; 
      
      try
      {             
         IntegracaoIpva integracaoIpva = new IntegracaoIpva(conn); 
         marca = new MarcaIntegracaoVo();
         marca.setCollVO(integracaoIpva.listarMarcaVeiculo());  
         if(!Validador.isCollectionValida(marca.getCollVO()))
         {
            throw new Exception(MensagemErro.ERRO_MARCA_VEICULO);
         }
                  
         return marca;      
      }
      catch (Exception e)
      {
         throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_IPVA, e);
      }
      catch (Error e)
      {
         throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_IPVA, e);
      }
      finally
      {
         if (this.conn != null)
         {
            this.close();
         }
      }
   }
   //TODO alteração
   public FichaVeiculoVo buscarCategoriaValorVenal(FichaVeiculoVo fichaveiculoVo)      
   throws IntegracaoException, ParametroObrigatorioException, SQLException
   {
      ValorVenalIntegracaoVo valorVenalIntegracaoVo = null;
      Vector vectorValoresVenal = null; 
      SefazDataHora dataAtual = new SefazDataHora();
      String NUMERO_CODIGO_CATEGORIA = "O código da categoria  é obrigatório.";    

      Validador.isObjetoValido(fichaveiculoVo);
      try
      {
           
         if (!Validador.isNumericoValido(fichaveiculoVo.getCategoria().getCodgCategoria()))
         {
            throw new ParametroObrigatorioException(NUMERO_CODIGO_CATEGORIA);
         }
              
         ValorVenalBe veiculoBe = new ValorVenalBe(conn);
         vectorValoresVenal = new Vector();
         vectorValoresVenal = veiculoBe.buscarCategoriaValorVenal(fichaveiculoVo.getCategoria().getCodgCategoria());
         
            Iterator ite = vectorValoresVenal.iterator();
            while (ite.hasNext())
            {
               ValorVenalVo  valorVenalVoAtual = (ValorVenalVo) ite.next();
               valorVenalIntegracaoVo = new ValorVenalIntegracaoVo(valorVenalVoAtual);
               if (valorVenalVoAtual.getAnoReferencia() == dataAtual.getAno() && fichaveiculoVo.getAnoFabricacao() == valorVenalVoAtual.getAnoFabricacao())
               {
                  fichaveiculoVo.setAnoFabricacao(valorVenalIntegracaoVo.getAnoFabricacao());
                  fichaveiculoVo.setAnoLicenciamento(valorVenalIntegracaoVo.getAnoReferencia());
                  fichaveiculoVo.setNomeProprietario(valorVenalIntegracaoVo.getNomeProprietario());
                  //fichaveiculoVo.getVeiculoVo().setCategoria(valorVenalIntegracaoVo.getCategoria());
                  fichaveiculoVo.setAnoReferencia(valorVenalIntegracaoVo.getAnoReferencia());
                  fichaveiculoVo.setValorVenal(valorVenalIntegracaoVo.getValrVeiculo());
                  fichaveiculoVo.setExisteValorVeiculo(true);
                  break;
               }
               fichaveiculoVo.setExisteValorVeiculo(false);
            } 
         return fichaveiculoVo;      
      }
     
      catch (ParametroObrigatorioException e)
      {
         throw e;
      }
      
      catch (Exception e)
      {
         throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_IPVA, e);
      }
      catch (Error e)
      {
         throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_IPVA, e);
      }
      finally
      {
         if (this.conn != null)
         {
            this.close();
         }
      }
   }
   // TODO - alteração
   public FichaVeiculoVo validarCampoFichaVeiculo(FichaVeiculoVo fichaveiculoVo)      
   {      
      fichaveiculoVo.setAnoLicenciamento(0);
      fichaveiculoVo.setNomeProprietario("");    
      fichaveiculoVo.setAnoReferencia(0);
      fichaveiculoVo.setValorVenal(0.0);
      return fichaveiculoVo;
   }

}


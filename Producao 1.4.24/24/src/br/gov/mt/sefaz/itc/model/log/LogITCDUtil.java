package br.gov.mt.sefaz.itc.model.log;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.model.log.itemhistorico.ItemHistoricoVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class LogITCDUtil
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   HistoricoLogVo historicoLogITCD;

   public LogITCDUtil(DomnTipoOperacao domnTipoOperacao)
   {
      historicoLogITCD = new HistoricoLogVo();
      historicoLogITCD.setDomnTipoOperacao(domnTipoOperacao);
      historicoLogITCD.setItemLog(new ItemHistoricoVo());
   }

   public LogITCDUtil()
   {
   }


   /**
    * Método resposável por buscar todos os métodos GET de uma classe através de reflexão.
    * 
    * Lógica : Realiza uma busca no objeto voClass listando todos os métodos
    * depois percorre a lista procurando os métodos GET e invoca o próximo método
    * continuar o processamento.
    * 
    * @param voClass tipo de classe dos objetos a serem comparados utilizado para fazer a reflexão.
    * @param voAtual objeto atual que está sendo alterado mas ainda não foi commitado as alterações.
    * @param voDB objeto retornado do banco sem alterações utilizado na comparação.
    * 
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public HistoricoLogVo rotinaGeracaoLog(Class voClass, Object voAtual, Object voDB)
   {
      try
      {

         // cria uma lista com todos os métodos.
         historicoLogITCD.setNomeTabela(obterNomeTabelaPelaAnotacao(obterAnotacaoClasse(voClass)[0]));

         Method[] metodos = obterMetodosDeclarados(voClass);
         // pecorre a lista.

         for (Method metodo: metodos)
         {
            // verifica se na assinatura do método contém a palavra (get).
            if (metodo.getName().contains("get"))
            {
               // retorna o anotação do método.
               Annotation[] anotacao = metodo.getDeclaredAnnotations();

               // verifica se o array contém alguma anotação.
               if (anotacao != null && anotacao.length >= 1)
               {
                  //System.out.print("COLUNA : " +obterNomeColuna(anotacao[0]));

                  String nomeColuna = obterNomeColuna(anotacao[0]);
                  String valorAnterior = compararValorAtributo(voAtual, voDB, metodo);

                  if ((nomeColuna != null & valorAnterior != null) && (!nomeColuna.equals("")))
                  {
                     ItemHistoricoVo item = new ItemHistoricoVo();
                     item.setNomeCampo(nomeColuna);
                     item.setValorAnterior(valorAnterior);
                     historicoLogITCD.getItemLog().getCollVO().add(item);
                  }
               }

            }
         }
      } catch (ClassNotFoundException ex)
      {
         System.out.println("error_04 : " + ex);
      } catch (IllegalArgumentException ex)
      {
         System.out.println("error_05 : " + ex);
      }

      return historicoLogITCD;
   }


   /**
    * Método responsável por capturar a nome da coluna
    * ou tabela definido no atributo (nomeColuna ou nomeTabela)
    * nas Anotações (AnotacaoAtributo, AnotacaoAtributoExterno e AnotacaoTabelaClasse )
    * Lógica : 
    * 1 - Verifica a instancia do objeto para descobrir qual o tipo da anotação.
    * 2 - Busca o atributo (nomeColuna ou nomeTabela).
    * 3 - Altera o valor da variavel (valorAtributo) para o valor encontrado.
    * 
    * @param anotacao anotação aplicada no método GET (obtida através de reflexão).
    * @return String retorna o valor do atributo (nomeColuna ou nomeTabela) definido durante aplicação
    * da anotação.
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   private String obterNomeColuna(Annotation anotacao)
   {
      String valorAtributo = "";
      if (anotacao instanceof AnotacaoAtributo)
      {
         AnotacaoAtributo anotacaoAtributo = (AnotacaoAtributo) anotacao;
         valorAtributo = anotacaoAtributo.nomeColuna();
      } else if (anotacao instanceof AnotacaoAtributoExterno)
      {
         AnotacaoAtributoExterno anotacaoAtributoExterno = (AnotacaoAtributoExterno) anotacao;
         AnotacaoColunaExterna[] anotacaoColunaExterna = anotacaoAtributoExterno.colunaReferencia();
         AnotacaoColunaExterna anotacaoTemp = anotacaoColunaExterna[0];
         valorAtributo = anotacaoTemp.nomeColuna();
      } else if (anotacao instanceof AnotacaoTabelaClasse)
      {
         AnotacaoTabelaClasse anotacaoTabelaClasse = (AnotacaoTabelaClasse) anotacao;
         AnotacaoIdentificador[] anotacaoIdentificador = anotacaoTabelaClasse.identificadores();
         AnotacaoIdentificador anotacaoIdentificadorTemp = anotacaoIdentificador[0];
         valorAtributo = anotacaoIdentificadorTemp.nomeColuna();
      }
      return valorAtributo;
   }

   private String obterNomeTabelaPelaAnotacao(Annotation anotacao)
   {
      String nomeTabela = "";
      if (anotacao instanceof AnotacaoTabelaClasse)
      {
         AnotacaoTabelaClasse anotacaoTabelaClasse = (AnotacaoTabelaClasse) anotacao;
         nomeTabela = anotacaoTabelaClasse.nomeTabela();
      }

      return nomeTabela;
   }


   private String compararValorAtributo(Object beanModificado, Object beanOriginal, Method metodo) throws ClassNotFoundException
   {
      String valor = null;
      try
      {
         Object objetoModificado = metodo.invoke(beanModificado);
         Object objetoOriginal = metodo.invoke(beanOriginal);

         if (historicoLogITCD.getDomnTipoOperacao().is(DomnTipoOperacao.ALTERACAO))
         {
            if (!objetoModificado.toString().trim().equals(objetoOriginal.toString().trim()))
            {
               valor = objetoOriginal.toString();
               if (objetoModificado instanceof AbstractDominioNumericoAbaco)
               {
                  if (obterTextoCorrenteDominio(objetoModificado).equalsIgnoreCase(obterTextoCorrenteDominio(objetoOriginal)))
                  {
                     valor = null;
                  } else
                  {
                     valor = obterTextoCorrenteDominio(objetoOriginal);
                  }
               }
            }
         }

         if (historicoLogITCD.getDomnTipoOperacao().is(DomnTipoOperacao.INCLUSAO))
         {
            valor = objetoModificado.toString();
            if (objetoModificado instanceof AbstractDominioNumericoAbaco)
            {
               if (obterTextoCorrenteDominio(objetoModificado).equalsIgnoreCase(obterTextoCorrenteDominio(objetoOriginal)))
               {
                  valor = null;
               } else
               {
                  valor = obterTextoCorrenteDominio(objetoModificado);
               }
            }
         }

         if (historicoLogITCD.getDomnTipoOperacao().is(DomnTipoOperacao.EXCLUSAO))
         {
            valor = objetoOriginal.toString();
            if (objetoModificado instanceof AbstractDominioNumericoAbaco)
            {
               if (obterTextoCorrenteDominio(objetoModificado).equalsIgnoreCase(obterTextoCorrenteDominio(objetoOriginal)))
               {
                  valor = null;
               } else
               {
                  valor = obterTextoCorrenteDominio(objetoModificado);
               }
            }
         }

         valor = classeListaLogVo(valor);

      } catch (IllegalAccessException ex)
      {
         System.out.println("error_01 : " + ex);
      } catch (IllegalArgumentException ex)
      {
         System.out.println("error_02 : " + ex);
      } catch (InvocationTargetException ex)
      {
         System.out.println("error_03 : " + ex);
      }
      return valor;
   }


   public void obterMetodosAcessores()
   {
      Class classeVo;
      try
      {
         classeVo = Class.forName(GIAITCDVo.class.getName());
         Method[] metodos = classeVo.getDeclaredMethods();

         for (int i = 0; i < metodos.length; i++)
         {
            System.out.println(metodos[i].getName());
         }

      } catch (ClassNotFoundException e)
      {
         System.out.println("Exception --- Classe não Encontrada....");
      }
   }

   private Method[] obterMetodosDeclarados(Class c) throws ClassNotFoundException
   {
      return Class.forName(c.getName()).getDeclaredMethods();
   }

   private Annotation[] obterAnotacaoClasse(Class c) throws ClassNotFoundException
   {
      return Class.forName(c.getName()).getDeclaredAnnotations();
   }

   private Method[] obterMetodos(Class c) throws ClassNotFoundException
   {
      return Class.forName(c.getName()).getMethods();
   }

   /**
    * Método responsável por centralizar o processo de busca
    * por alterações nos dados da GIA.
    * 
    * 
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   private void verificarAlteracao(Class voClass, Object voAtual, Object voLog)
   {

      rotinaGeracaoLog(voClass, voAtual, voLog);
   }

   /**
    * Responsavel por recuperar o valores de um dominio
    * quando o dominio foi convertido para um <b>Object</b>
    * <br>
    * Logica: Faz um cast explicito de <b>Object</b> para 
    * <b>br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco</b>
    * e invoca o metodo getTextoCorrente();
    * 
    *
    * @param dominio Dominio que foi convertido para Object.
    * @return
    */
   private String obterTextoCorrenteDominio(Object dominio)
   {
      if (dominio instanceof AbstractDominioNumericoAbaco)
      {
         AbstractDominioNumericoAbaco absdominio = (AbstractDominioNumericoAbaco) dominio;
         return absdominio.getTextoCorrente().trim();
      }

      return "";
   }

   private String classeListaLogVo(String nomeQualificadoClasse)
   {
      if (nomeQualificadoClasse == null)
      {
         return nomeQualificadoClasse;
      }
      if (true)
      {
         System.out.println(" C1 : " + nomeQualificadoClasse);
         nomeQualificadoClasse = null;
         System.out.println(" C2 : " + nomeQualificadoClasse);
      }
      return nomeQualificadoClasse;
   }

}

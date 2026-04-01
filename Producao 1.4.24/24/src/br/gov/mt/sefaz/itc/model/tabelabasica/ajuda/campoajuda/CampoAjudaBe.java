/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CampoAjudaBe.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: CampoAjudaBe.java,v 1.1.1.1 2008/05/28 17:55:05 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.campoajuda;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Classe de negócio para os campos da ajuda.
 * @author Marlo Eichenberg Motta
 * @author Thiago de Castilho Pacheco
 */
public class CampoAjudaBe extends AbstractBe
{
	/**
	 * Construtor da classe.
	 * Este construtor não abre uma conexão com o banco de dados, mas utiliza a conexão recebida como parâmetro para realizar suas validações.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public CampoAjudaBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Construtor padrão da classe.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public CampoAjudaBe() throws SQLException
	{
		super();
	}

	/**
	 * Método utilizado para Listar Campo Ajuda.
	 * @param campoAjudaVo Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public CampoAjudaVo listarCampoAjuda(final CampoAjudaVo campoAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(campoAjudaVo);
		(new CampoAjudaQDao(conn)).listCampoAjuda(campoAjudaVo);
		return campoAjudaVo;
	}

	/**
	 * Método utilizado para Consultar Campo Ajuda
	 * @param campoAjudaVo Campo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public CampoAjudaVo consultarCampoAjuda(final CampoAjudaVo campoAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(campoAjudaVo);
		(new CampoAjudaQDao(conn)).findCampoAjuda(campoAjudaVo);
		return campoAjudaVo;
	}
}

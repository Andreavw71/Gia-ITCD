/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: TelaAjudaBe.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: TelaAjudaBe.java,v 1.1.1.1 2008/05/28 17:55:06 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telaajuda;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Componente de negócio de Tela de Ajuda
 * @author Thiago de Castilho Pacheco
 */
public class TelaAjudaBe extends AbstractBe
{
	/**
	 * Construtor da classe.
	 * Este construtor não abre uma conexão com o banco de dados, mas utiliza a conexão recebida como parâmetro para realizar suas validações.
	 * @param conexao
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaAjudaBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Construtor padrão da classe.
	 * @throws SQLException
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaAjudaBe() throws SQLException
	{
		super();
	}

	/**
	 * Método utilizado para Listar Tela Ajuda
	 * @param telaAjudaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaAjudaVo listarTelaAjuda(final TelaAjudaVo telaAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(telaAjudaVo);
		(new TelaAjudaQDao(conn)).listTelaAjuda(telaAjudaVo);
		return telaAjudaVo;
	}

	/**
	 * Método utilizado para Consultar Tela Ajuda
	 * @param telaAjudaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public TelaAjudaVo consultarTelaAjuda(final TelaAjudaVo telaAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(telaAjudaVo);
		(new TelaAjudaQDao(conn)).findTelaAjuda(telaAjudaVo);
		return telaAjudaVo;
	}
}

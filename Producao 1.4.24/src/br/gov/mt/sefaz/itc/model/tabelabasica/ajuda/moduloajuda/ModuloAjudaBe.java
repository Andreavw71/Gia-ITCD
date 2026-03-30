/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ModuloAjudaBe.java
 * Revisão: Leandro Dorileo
 * Data revisão: 20/03/2008
 * $Id: ModuloAjudaBe.java,v 1.1.1.1 2008/05/28 17:55:06 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.moduloajuda;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Classe de negócio de módulo
 * @author Thiago de Castilho Pacheco
 */
public class ModuloAjudaBe extends AbstractBe
{
	/**
	 * Construtor da classe.
	 * Este construtor não abre uma conexão com o banco de dados, mas utiliza a conexão recebida como parâmetro para realizar suas validações.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ModuloAjudaBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Construtor padrão da classe.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ModuloAjudaBe() throws SQLException
	{
		super();
	}

	/**
	 * Método utilizado para Listar Módulo Ajuda.
	 * @param moduloAjudaVo Módulo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ModuloAjudaVo listarModuloAjuda(final ModuloAjudaVo moduloAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(moduloAjudaVo);
		(new ModuloAjudaQDao(conn)).listModuloAjuda(moduloAjudaVo);
		return moduloAjudaVo;
	}

	/**
	 * Método utilizado para Consultar Módulo Ajuda.
	 * @param moduloAjudaVo Módulo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ModuloAjudaVo consultarModuloAjuda(final ModuloAjudaVo moduloAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(moduloAjudaVo);
		(new ModuloAjudaQDao(conn)).findModuloAjuda(moduloAjudaVo);
		return moduloAjudaVo;
	}

	/**
	 * Método utilizado para Listar Módulo Ajuda.
	 * @param subModuloAjudaVo Sub-Módulo Ajuda (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public ModuloAjudaVo listarSubModuloAjuda(final ModuloAjudaVo subModuloAjudaVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(subModuloAjudaVo);
		(new ModuloAjudaQDao(conn)).listSubModuloAjuda(subModuloAjudaVo);
		return subModuloAjudaVo;
	}

	public void solicitaInserirModulo(ModuloAjudaVo moduloAtual)
	{
	}
}

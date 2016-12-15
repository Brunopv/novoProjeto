package br.com.prova.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import br.com.prova.entidade.Usuario;
import br.com.prova.factory.ConnectionFactory;

public class UsuarioDAO extends ConnectionFactory {

	public void removerTodos() {
		Connection conexao;
		PreparedStatement pstmt = null;

		conexao = criarConexao();

		try {
			pstmt = conexao.prepareStatement("delete from users");
			pstmt.execute();

		} catch (Exception e) {
			System.out.println("erro ao remover registros" + e);
		} finally {
			fecharConexao(conexao, pstmt, null);
		}
	}
}

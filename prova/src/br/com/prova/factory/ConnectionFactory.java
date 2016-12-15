package br.com.prova.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection criarConexao() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/prova", "root", "root");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void fecharConexao(Connection conn, PreparedStatement pstmt, ResultSet rs) {

		try {
			if (conn != null) {
				conn.close();
			}

			if (pstmt != null) {
				conn.close();
			}

			if (rs != null) {
				rs.close();
			}

		} catch (Exception e) {
			System.out.println("Erro ao fechar conexão com banco de dados");
		}
	}
}

package br.com.prova.testes;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.prova.factory.ConnectionFactory;

public class TesteConexao {

	// teste para verificar conex�o banco mysql
	public static void main(String[] args) {
		Connection connection = new ConnectionFactory().criarConexao();
		System.out.println("Conex�o aberta!");
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

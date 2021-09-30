package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.dev.rodrigocury.models.Produto;

public class TestaListagem {
	public static void main(String[] args) {
		try (Connection connection = ConnectioFactory.create()) {

			Statement statement = connection.createStatement();
			
			statement.execute("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
			
			ResultSet resultSet = statement.getResultSet();
			
			ArrayList<Produto> produtos = new ArrayList<Produto>();
			
			while(resultSet.next()) {
				Integer id = resultSet.getInt("ID");
				String nome = resultSet.getString("nome");
				String descricao = resultSet.getString("descricao");
				produtos.add(new Produto(id, nome, descricao));
			}
			
			produtos.forEach(System.out::println);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

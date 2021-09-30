package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.dev.rodrigocury.models.Produto;

public class TestaListagemComParametros {

	public static void main(String[] args) {
		try (Connection connection = ConnectioFactory.create()) {

			PreparedStatement statement = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
			statement.execute();
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

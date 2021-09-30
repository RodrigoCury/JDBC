package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.dev.rodrigocury.models.Produto;

public class TestaListagem {
	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC";
		String user = "root";
		String env_key = "MYSQL_PASS";
        String password = System.getenv(env_key);
		

		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			
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
			
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

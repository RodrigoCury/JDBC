package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import br.dev.rodrigocury.factories.ConnectioFactory;

public class TestaInsercao {

	public static void main(String[] args) {
		String nome = "Mouse G305";
		String descricao = "Mouse Sem fio Lightning Logitech G305";
		
		try (Connection connection = ConnectioFactory.create()){
			
			Statement stm = connection.createStatement();
			System.out.println(String.format("INSERT INTO PRODUTO (nome, descricao) VALUES ('%s', '%s')", nome, descricao));
			stm.execute(String.format("INSERT INTO PRODUTO (nome, descricao) VALUES ('%s', '%s')", nome, descricao));
			
			ResultSet rst = stm.getGeneratedKeys();
			while (rst.next()){
				Integer id= rst.getInt("id");
				System.out.println(String.format("Created ID: %d", id));
			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

}

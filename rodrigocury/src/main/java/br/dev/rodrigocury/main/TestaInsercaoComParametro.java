package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = ConnectioFactory.create();
		PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
		
		try {
			connection.setAutoCommit(false);
			
			extracted("Tv Smart", "LG 45" , stm);
			extracted("Radin", "Bateria", stm);
			
			connection.commit();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Rollback Aconteceu!");
			connection.rollback();
		} finally {
			stm.close();
			connection.close();
		}
	}

	private static void extracted(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);

		stm.execute();
		
		try (ResultSet rst = stm.getGeneratedKeys()){
			while (rst.next()){
				Integer id= rst.getInt(1);
				System.out.println(String.format("Created ID: %d", id));
			}			
		}
				
	}

}

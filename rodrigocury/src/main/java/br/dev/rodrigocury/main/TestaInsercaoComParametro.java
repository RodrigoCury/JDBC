package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestaInsercaoComParametro {

	public static void main(String[] args) {
		String nome = "Mouse G305";
		String descricao = "Mouse Sem fio Lightning Logitech G305'";
		
		try (Connection connection = ConnectioFactory.create()){
			
			PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setString(1, nome);
			stm.setString(2, descricao);

			stm.execute();
			
			ResultSet rst = stm.getGeneratedKeys();
			while (rst.next()){
				Integer id= rst.getInt(1);
				System.out.println(String.format("Created ID: %d", id));
			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}

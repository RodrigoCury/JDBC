package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.dev.rodrigocury.factories.ConnectioFactory;

public class TestaRemocaoComParametro {

	public static void main(String[] args) {
		Integer id = 3;
		
		try (Connection connection = ConnectioFactory.create()) {

			PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID = ?");
			
			stm.setInt(1, id);
			
			stm.execute();

			Integer modifiedLines = stm.getUpdateCount();
					
			System.out.println(String.format("Linhas Modificadas: %d", modifiedLines));
			

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}

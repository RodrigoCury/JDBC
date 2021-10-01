package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.Statement;

import br.dev.rodrigocury.factories.ConnectioFactory;

public class TestaRemocao {

	public static void main(String[] args) {
		Integer id = 4;
		
		try (Connection connection = ConnectioFactory.create()) {

			Statement stm = connection.createStatement();
			
			stm.execute(String.format("DELETE FROM PRODUTO WHERE ID = %d", id));

			Integer modifiedLines = stm.getUpdateCount();
					
			System.out.println(String.format("Linhas Modificadas: %d", modifiedLines));
			

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}

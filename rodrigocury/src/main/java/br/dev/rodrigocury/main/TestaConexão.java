package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexão {

	public static void main(String[] args) {
		try {
			Connection connection = ConnectioFactory.create();
			
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

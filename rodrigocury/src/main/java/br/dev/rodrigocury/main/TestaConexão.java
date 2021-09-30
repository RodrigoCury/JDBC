package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexão {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC";
		String user = "root";
		String env_key = "MYSQL_PASS";
        String password = System.getenv(env_key);
		

		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			
			
			
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

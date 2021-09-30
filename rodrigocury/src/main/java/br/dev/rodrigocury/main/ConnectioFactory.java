package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectioFactory {
	public static Connection create() throws SQLException {
		String url = "jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC";
		String user = "root";
		String env_key = "MYSQL_PASS";
        String password = System.getenv(env_key);
        
        return DriverManager.getConnection(url, user, password);
	}
}

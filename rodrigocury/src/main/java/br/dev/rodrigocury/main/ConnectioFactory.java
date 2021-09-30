package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectioFactory {
	
	public static DataSource dataSource;

	private static String url = "jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC";
	private static String user = "root";
	private static String env_key = "MYSQL_PASS";
	private static String password = System.getenv(env_key);
	
	static {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl(url);
		cpds.setUser(user);
		cpds.setPassword(password);
		dataSource = cpds;
	}
	
	public static Connection create() throws SQLException {
        return dataSource.getConnection();
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
}

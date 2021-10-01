package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.SQLException;

import br.dev.rodrigocury.factories.ConnectioFactory;

public class TestaPoolConexoes {

	public static void main(String[] args) {
		
		// Abre as vinte
		for (int i = 0; i < 20; i++) {
			try (Connection c = ConnectioFactory.create()){
				
				System.out.println("Conexao criada: #" + i);
				
			} catch (SQLException e) {
				System.out.println("ERRO");
			}
		}
		
		// Não Abre as vinte
		for (int i = 0; i < 20; i++) {
			try {
				Connection c = ConnectioFactory.create();
				System.out.println("Conexao criada: #" + i);
				
			} catch (SQLException e) {
				System.out.println("ERRO");
			}
		}
		
	}

}

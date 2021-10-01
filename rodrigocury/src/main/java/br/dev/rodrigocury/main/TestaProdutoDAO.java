package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.SQLException;

import br.dev.rodrigocury.DAO.ProdutoDAO;
import br.dev.rodrigocury.factories.ConnectioFactory;

public class TestaProdutoDAO {

	public static void main(String[] args) {
		
		
		try (Connection c = ConnectioFactory.create()){
			
			ProdutoDAO pd = new ProdutoDAO(c);
			
//			pd.salvarProduto("Banana", "de Pijama");
//			
//			pd.listar().forEach(System.out::println);
//			
//			pd.removeProduto(12);
//			
//			pd.listar().forEach(System.out::println);
			
			pd.buscar(1).forEach(System.out::println);;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

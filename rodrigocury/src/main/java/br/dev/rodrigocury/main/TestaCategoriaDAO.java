package br.dev.rodrigocury.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.dev.rodrigocury.DAO.CategoriaDAO;
import br.dev.rodrigocury.models.Categoria;

public class TestaCategoriaDAO {

	public static void main(String[] args) {
		
		try (Connection c = ConnectioFactory.create()){
			
			CategoriaDAO cd = new CategoriaDAO(c);
			ArrayList<Categoria> ac = cd.listar();
			
			ac.forEach(System.out::println);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

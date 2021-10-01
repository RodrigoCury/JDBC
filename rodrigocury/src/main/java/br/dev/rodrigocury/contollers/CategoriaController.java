package br.dev.rodrigocury.contollers;

import java.util.List;

import br.dev.rodrigocury.DAO.CategoriaDAO;
import br.dev.rodrigocury.factories.ConnectioFactory;
import br.dev.rodrigocury.models.Categoria;

public class CategoriaController {
	
	private CategoriaDAO categoriaDAO;
	
	public CategoriaController() {
		categoriaDAO = new CategoriaDAO(ConnectioFactory.create());
	}

	public List<Categoria> listar() {
		
		List<Categoria> categorias = categoriaDAO.listar();
		
		return categorias;
	}
}

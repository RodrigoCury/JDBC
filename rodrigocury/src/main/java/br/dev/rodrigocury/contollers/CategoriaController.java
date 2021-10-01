package br.dev.rodrigocury.contollers;

import java.util.ArrayList;
import java.util.List;

import br.dev.rodrigocury.models.Categoria;

public class CategoriaController {

	public List<Categoria> listar() {
		List<Categoria> categorias = 
				new ArrayList<Categoria>();
		categorias.add(new Categoria("Categoria de teste", 1)); 
		return categorias;
	}
}

package br.dev.rodrigocury.contollers;

import java.util.List;

import br.dev.rodrigocury.DAO.ProdutoDAO;
import br.dev.rodrigocury.factories.ConnectioFactory;
import br.dev.rodrigocury.models.Produto;

public class ProdutoController {
	
	private ProdutoDAO produtoDAO;
	
	public ProdutoController() {
		this.produtoDAO = new ProdutoDAO(ConnectioFactory.create());
	}

	public void deletar(Integer id) {
		produtoDAO.removeProduto(id);
	}

	public void salvar(Produto produto) {
		produtoDAO.salvarProduto(produto);
	}

	public List<Produto> listar() {
		List<Produto> produtos = produtoDAO.listar();
		produtos.add(new Produto("Nome do Produto de teste"
				, "Descrição do produto de teste"));
		return produtos;
	}

	public void alterar(String nome, String descricao, Integer id) {
		produtoDAO.alterar(nome, descricao, id);
	}
}

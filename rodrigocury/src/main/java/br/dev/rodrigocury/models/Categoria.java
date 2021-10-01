package br.dev.rodrigocury.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Categoria {
	private String nome;
	private Integer id;
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	
	public List<Produto> getProdutos() {
		return Collections.unmodifiableList(produtos);
	}

	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public Categoria(String nome, Integer id) {
		this.nome = nome;
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.id != null ? String.format("#%d - %s", this.id, this.nome) : String.format("## - %s", this.nome);
	}
	
	public void addProduto(Produto p) {
		produtos.add(p);
	}
	
	
}

package br.dev.rodrigocury.models;

public class Categoria {
	private String nome;
	private Integer id;
	
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
	
	
}

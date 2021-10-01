package br.dev.rodrigocury.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.dev.rodrigocury.models.Categoria;
import br.dev.rodrigocury.models.Produto;

public class CategoriaDAO {

	private Connection connection;

	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvarCategoria(Categoria c) throws SQLException {
		String sql = "INSERT INTO CATEGORIA (nome) Values (?)";
		
		try(PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
			ps.setString(1, c.getNome());
						
			ps.execute();
			
			try(ResultSet rs = ps.getGeneratedKeys()){
				if(rs.next()) {
					c.setId(rs.getInt(1));
				}
			}
			
		}
	}
	
	public void salvarCategoria(String nome) throws SQLException {
		this.salvarCategoria(new Categoria(nome));
	}
	
	public ArrayList<Categoria> listar() throws SQLException {
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();

		String sql = "SELECT ID, NOME FROM CATEGORIA";
						
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.execute();
			
			try(ResultSet resultSet = statement.getResultSet()){
				
				while(resultSet.next()) {
					Integer id = resultSet.getInt("ID");
					String nome = resultSet.getString("nome");
					categorias.add(new Categoria(nome, id));
				}
				
			}
			
		}
		return categorias;
	}
	
	public void removeCategoria(Integer id) throws SQLException{
		String sql = "DELETE FROM CATEGORIA WHERE ID = ?"; 
		
		try (PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setInt(1, id);
			
			stm.execute();
			
			Integer modifiedLines = stm.getUpdateCount();
			
			System.out.println(String.format("Linhas Modificadas: %d", modifiedLines));
		}
		
				
	}
	
	public ArrayList<Categoria> listarComProdutos() throws SQLException{
		Categoria ultimaCategoria = null;
		
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();

		String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN PRODUTO P ON C.ID = P.CATEGORIA_ID";
						
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.execute();
			
			try(ResultSet resultSet = statement.getResultSet()){
				
				while(resultSet.next()) {
					if (ultimaCategoria == null || !ultimaCategoria.getNome().equals(resultSet.getString(2))) {						
						Integer id = resultSet.getInt("ID");
						String nome = resultSet.getString("nome");
						Categoria categoria = new Categoria(nome, id);
						categorias.add(categoria);
						ultimaCategoria = categoria;
					}
					
					Integer idProduto = resultSet.getInt(3);
					String nomeProduto = resultSet.getString(4);
					String descricao = resultSet.getString(5);
					
					ultimaCategoria.addProduto(new Produto(idProduto, nomeProduto, descricao));
					
				}
				
			}
			
		}
		return categorias;
	}
}

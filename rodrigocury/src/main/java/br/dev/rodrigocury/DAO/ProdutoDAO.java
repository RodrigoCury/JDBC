package br.dev.rodrigocury.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.dev.rodrigocury.models.Produto;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvarProduto(Produto p) throws SQLException {
		String sql = "INSERT INTO PRODUTO (nome, descricao) Values (?,?)";
		
		try(PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
			ps.setString(1, p.getNome());
			ps.setString(2, p.getDescricao());
			
			ps.execute();
			
			try(ResultSet rs = ps.getGeneratedKeys()){
				if(rs.next()) {
					p.setId(rs.getInt(1));
				}
			}
			
		}
	}
	
	public void salvarProduto(String nome, String descricao) throws SQLException {
		this.salvarProduto(new Produto(nome, descricao));
	}
	
	public ArrayList<Produto> listar() throws SQLException {
		ArrayList<Produto> produtos = new ArrayList<Produto>();

		String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";
						
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.execute();
			
			
			try(ResultSet resultSet = statement.getResultSet()){
				
				while(resultSet.next()) {
					Integer id = resultSet.getInt("ID");
					String nome = resultSet.getString("nome");
					String descricao = resultSet.getString("descricao");
					produtos.add(new Produto(id, nome, descricao));
				}
				
			}
			
		}
		return produtos;
	}
	
	public void removeProduto(Integer id) throws SQLException{
		String sql = "DELETE FROM PRODUTO WHERE ID = ?"; 
		
		try (PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setInt(1, id);
			
			stm.execute();
			
			Integer modifiedLines = stm.getUpdateCount();
			
			System.out.println(String.format("Linhas Modificadas: %d", modifiedLines));
		}
		
				
	}
}

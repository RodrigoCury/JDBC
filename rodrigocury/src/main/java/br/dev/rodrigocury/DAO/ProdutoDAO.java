package br.dev.rodrigocury.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.dev.rodrigocury.models.Categoria;
import br.dev.rodrigocury.models.Produto;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvarProduto(Produto p) {
		String sql = "INSERT INTO PRODUTO (nome, descricao, categoria_id) Values (?,?,?)";

		try (PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, p.getNome());
			ps.setString(2, p.getDescricao());
			ps.setInt(3, p.getCategoriaID());

			ps.execute();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					p.setId(rs.getInt(1));
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void salvarProduto(String nome, String descricao, Integer CategoriaID) {
		Produto p = new Produto(nome, descricao);
		p.setCategoriaID(CategoriaID);
		this.salvarProduto(p);
	}

	public ArrayList<Produto> listar() {
		ArrayList<Produto> produtos = new ArrayList<Produto>();

		String sql = "SELECT ID, NOME, DESCRICAO, CATEGORIA_ID FROM PRODUTO";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.execute();

			try (ResultSet resultSet = statement.getResultSet()) {

				while (resultSet.next()) {
					Integer id = resultSet.getInt("ID");
					String nome = resultSet.getString("nome");
					String descricao = resultSet.getString("descricao");
					Integer categoriaID = resultSet.getInt("categoria_id");
					Produto p = new Produto(id, nome, descricao);
					p.setCategoriaID(categoriaID);
					produtos.add(p);
				}

			}

			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void removeProduto(Integer id) {
		String sql = "DELETE FROM PRODUTO WHERE ID = ?";

		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);

			stm.execute();

			Integer modifiedLines = stm.getUpdateCount();

			System.out.println(String.format("Linhas Modificadas: %d", modifiedLines));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void alterar(String nome, String descricao, Integer id) {
		String sql = "UPDATE PRODUTO P SET P.NOME = ?, P.DESCRICAO = ? WHERE ID = ?";
		try (PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, nome);
			stm.setString(2, descricao);
			stm.setInt(3, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Deprecated
	public ArrayList<Produto> buscar(Categoria ct) {
		return buscar(ct.getId());
	}

	@Deprecated // N + 1 Calls
	public ArrayList<Produto> buscar(Integer id) {
		ArrayList<Produto> produtos = new ArrayList<Produto>();

		String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			statement.execute();

			try (ResultSet resultSet = statement.getResultSet()) {

				while (resultSet.next()) {
					Integer id1 = resultSet.getInt("ID");
					String nome = resultSet.getString("nome");
					String descricao = resultSet.getString("descricao");
					produtos.add(new Produto(id1, nome, descricao));
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return produtos;
	}

	}

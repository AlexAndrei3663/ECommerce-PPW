package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Categoria;
import Modelo.Produto;

public class CategoriaDAO {
	
	public static void adicionaCategoria(Categoria categoria){
		String sql = "insert into categoria " +
				"(nome)" +
				" values (?)";
		try {
			// prepared statement para insersão
			Connection con = GerenciadorConexao.getConnetion();
			PreparedStatement stmt = con.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,categoria.getNome());
			// executa
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	

	public static List<Categoria> buscaCategorias(){
		PreparedStatement stmt;
		List<Categoria>categorias = new ArrayList<Categoria>();
		try {
			Connection con = GerenciadorConexao.getConnetion();
			stmt = con.prepareStatement("select * from categoria");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto Contato
				Categoria categoria = new Categoria();
				categoria.setCodCategoria(rs.getInt("cod"));
				categoria.setNome(rs.getString("nome"));
				
				categorias.add(categoria);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(categorias.isEmpty())
			return null;
		else
			return categorias;
		
	}
	
	public static Categoria buscaCategoria(int cod){
		 Categoria c = null;
		 Connection con = GerenciadorConexao.getConnetion();
		
		try {
			
			PreparedStatement stmt = con.prepareStatement("select * from categoria where cod=?");
			stmt.setInt(1,cod);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			c = new Categoria();
			c.setCodCategoria(cod);
			c.setNome(rs.getString("nome"));
			
			
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return c;
	}
}

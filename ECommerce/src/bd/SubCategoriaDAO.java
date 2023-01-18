package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Categoria;
import Modelo.SubCategoria;

public class SubCategoriaDAO {
	
	public static void adicionaSubCategoria(int codCategoria, String nomeSubCategoria){
		String sql = "insert into subcategoria " +
				"(nome, cod_categoria)" +
				" values (?,?)";
		try {
			// prepared statement para insersão
			Connection con = GerenciadorConexao.getConnetion();
			PreparedStatement stmt = con.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,nomeSubCategoria);
			stmt.setInt(2,codCategoria);
			// executa
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	

	
	public static List<SubCategoria> buscaSubCategorias(int cod){
		
		PreparedStatement stmt;
		List<SubCategoria>listaSubCategorias = new ArrayList<SubCategoria>();
		try {
			Connection con = GerenciadorConexao.getConnetion();
			stmt = con.prepareStatement("select * from subcategoria where cod_categoria="+cod);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto Contato
				SubCategoria subCategoria = new SubCategoria();
				subCategoria.setCodSubCategoria(rs.getInt("cod"));
				subCategoria.setNome(rs.getString("nome"));
				subCategoria.setCategoria(CategoriaDAO.buscaCategoria(rs.getInt("cod_categoria")));
				
				listaSubCategorias.add(subCategoria);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(listaSubCategorias.isEmpty())
			return null;
		else
			return listaSubCategorias;
	}
	public static SubCategoria buscaSubCategoria(int cod){
		 
			SubCategoria sc = null;
		 Connection con = GerenciadorConexao.getConnetion();
		
		try {
			
			PreparedStatement stmt = con.prepareStatement("select * from subcategoria where cod=?");
			stmt.setInt(1,cod);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			sc = new SubCategoria();
			sc.setCodSubCategoria(cod);
			sc.setNome(rs.getString("nome"));
			sc.setCategoria(CategoriaDAO.buscaCategoria(rs.getInt("cod_categoria")));
			
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return sc;
	}
}

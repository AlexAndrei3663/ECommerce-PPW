package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Imagem;
import Modelo.Produto;


public class ProdutoDAO {
	public static void adicionaProduto(Produto produto){
		String sql = "insert into produto " +
				"(nome, valor, cod_subcategoria, imagemproduto)" +
				" values (?,?,?,?)";
		try {
			// prepared statement para insersão
			Connection con = GerenciadorConexao.getConnetion();
			PreparedStatement stmt = con.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,produto.getNome());
			stmt.setDouble(2,produto.getValor());
			stmt.setInt(3,produto.getSubCategoria().getCodSubCategoria());
			stmt.setBinaryStream(4,  produto.getImagem().getImagem(), produto.getImagem().getTamanhoImagem());
			// executa
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	

	public static void deleteProduto(int codigo){

		try {
			Connection con = GerenciadorConexao.getConnetion();
			PreparedStatement stmt = con.prepareStatement("delete from produto where cod=?");
			stmt.setLong(1, codigo);
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<Produto> listaProdutos(){
		PreparedStatement stmt;
		List<Produto> products = new ArrayList<Produto>();
		try {
			Connection con = GerenciadorConexao.getConnetion();
			stmt = con.prepareStatement("select * from produto");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto Contato
				Produto product = new Produto();
				product.setCod(rs.getInt("cod"));
				product.setNome(rs.getString("nome"));
				product.setValor(rs.getDouble("valor"));
				product.setSubCategoria(SubCategoriaDAO.buscaSubCategoria(rs.getInt("cod_subcategoria")));
				products.add(product);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(products.isEmpty())
			return null;
		else
			return products;
	}

	
		public static  Produto buscaProduto(int codigo){
			Produto p = null;
			Connection con = GerenciadorConexao.getConnetion();
			try {
				PreparedStatement stmt = con.prepareStatement("select * from produto where cod=?");
				stmt.setInt(1, codigo);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				
				p = new Produto();
				p.setCod(codigo);
				p.setNome(rs.getString("nome"));
				p.setValor(rs.getDouble("valor"));
                p.setSubCategoria(SubCategoriaDAO.buscaSubCategoria(rs.getInt("cod_subcategoria")));
				
                Imagem imagem = new Imagem();
                imagem.setImagem(rs.getBinaryStream("imagemproduto"));
                imagem.setTamanhoImagem((int)rs.getBytes("imagemproduto").length);
				
                p.setImagem(imagem);
                
				stmt.close();
				con.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return p;
			
		}
		
		public static void alterarProduto(Produto produto){
			
			String sql = "update produto set nome=?, valor=?, cod_subcategoria=?" +
					"where cod=?";
			
			
				Connection con = GerenciadorConexao.getConnetion();
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, produto.getNome());
					stmt.setDouble(2, produto.getValor());
					stmt.setInt(3, produto.getSubCategoria().getCodSubCategoria());
					stmt.setInt(4, produto.getCod());
					stmt.execute();
					stmt.close();
					con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}	

		public static List<Produto> buscaProdutosPorCategoria(int cod){
	        
	        PreparedStatement stmt;
	        List<Produto> produtos = new ArrayList<Produto>();
	        try {
	            
	            Connection con = GerenciadorConexao.getConnetion();
	            stmt = con.prepareStatement("select * from produto p " +
	                                        "inner join subcategoria s on " +
	                                        "p.cod_subcategoria = s.cod " +
	                                        "inner join categoria c on " +
	                                        "c.cod = s.cod_categoria " +
	                                        "where c.cod = " + cod +
	                                        " order by p.cod");
	            
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                // criando o objeto Produto
	                Produto produto = new Produto();
	                produto.setCod(rs.getInt("cod"));
	                produto.setNome(rs.getString("nome"));
	                produto.setValor(rs.getDouble("valor"));
	                produto.setSubCategoria(SubCategoriaDAO.buscaSubCategoria(rs.getInt("cod_subcategoria")));
	                produtos.add(produto);
	            }
	            rs.close();
	            stmt.close();
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        if(produtos.isEmpty())
	            return null;
	        else
	            return produtos;
	        
	    }
	    
	    public static List<Produto> buscaProdutoPorSubCategoria(int cod){
	        
	        PreparedStatement stmt;
	        List<Produto> produtos = new ArrayList<Produto>();
	        try {
	            
	            Connection con = GerenciadorConexao.getConnetion();
	            stmt = con.prepareStatement("select * from produto where cod_subcategoria = " + cod +
	                    " order by cod");
	            
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                // criando o objeto Produto
	                Produto produto = new Produto();
	                produto.setCod(rs.getInt("cod"));
	                produto.setNome(rs.getString("nome"));
	                produto.setValor(rs.getDouble("valor"));
	                produto.setSubCategoria(SubCategoriaDAO.buscaSubCategoria(rs.getInt("cod_subcategoria")));
	                produtos.add(produto);
	            }
	            rs.close();
	            stmt.close();
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        if(produtos.isEmpty())
	            return null;
	        else
	            return produtos;
	        
	    }
}
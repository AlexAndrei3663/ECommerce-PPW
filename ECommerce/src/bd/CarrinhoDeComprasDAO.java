package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





import Modelo.CarrinhoDeCompras;
public class CarrinhoDeComprasDAO {

		
		public static int verificaProdutoCarrinho(int cod, int codCliente){

		PreparedStatement stmt;

		CarrinhoDeCompras produtoCarrinho = null;

		try {

				Connection con = GerenciadorConexao.getConnetion();
				stmt = con.prepareStatement("select * from carrinhocompras where cod_produto=? and cod_cliente=?"); 

				stmt.setInt(1, cod);
				stmt.setInt(2, codCliente);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {

					// criando o objeto Contato

					produtoCarrinho = new CarrinhoDeCompras();
					produtoCarrinho.setCod(rs.getInt("cod"));					
					produtoCarrinho.setUsuario(UsuarioDAO.getCliente(rs.getInt("cod_cliente")));
					produtoCarrinho.setProduto(ProdutoDAO.buscaProduto(rs.getInt("cod_produto")));
					produtoCarrinho.setQuantidade(rs.getInt("quantidade"));
				
				}

				rs.close();
				stmt.close();
				con.close();
				} catch (SQLException e) {

					e.printStackTrace();

				}		
					if(produtoCarrinho == null)

							return 0;
					else

						return produtoCarrinho.getQuantidade();
				}
	
	
	
	public static void adicionaProdutoCarrinho(int codProduto, int codCliente){
		String sql;
		Connection con = GerenciadorConexao.getConnetion();

		PreparedStatement stmt = null;
		try{

			int quantidadeProdutoBD = verificaProdutoCarrinho(codProduto,codCliente);
			
			System.out.println("Quantidade: " + quantidadeProdutoBD);

			if(quantidadeProdutoBD == 0){
				sql = "insert into carrinhocompras " +
				"(cod_cliente, cod_produto, quantidade,valor)" +
				" values (?,?,?,?)";

				stmt = con.prepareStatement(sql);
				stmt.setInt(1,codCliente);
				stmt.setInt(2,codProduto);
				stmt.setInt(3, 1);
				stmt.setDouble(4, ProdutoDAO.buscaProduto(codProduto).getValor());
				}
				else{
				sql = "update carrinhocompras set quantidade=?,valor=? where cod_produto=? and cod_cliente=?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1,quantidadeProdutoBD+1);
				stmt.setDouble(2, ProdutoDAO.buscaProduto(codProduto).getValor()*(quantidadeProdutoBD+1));
				stmt.setInt(3,codProduto);
				stmt.setInt(4, codCliente);

				}// executa

				stmt.execute();
				stmt.close();
				con.close();

				} catch (SQLException e) {

					throw new RuntimeException(e);
				}	
	}
	public static List<CarrinhoDeCompras> pegaProdutosCarrinho(int codCliente){
		PreparedStatement stmt;

		List<CarrinhoDeCompras> carrinho = new ArrayList<CarrinhoDeCompras>();

		try {
			Connection con = GerenciadorConexao.getConnetion();		
			stmt = con.prepareStatement("select * from carrinhocompras where cod_cliente=?");
			stmt.setInt(1, codCliente);
			ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

		// criando o objeto Contato

		CarrinhoDeCompras produtoCarrinho = new CarrinhoDeCompras();
		
		produtoCarrinho.setCod(rs.getInt("cod"));
		produtoCarrinho.setUsuario(UsuarioDAO.getCliente(rs.getInt("cod_cliente")));
		produtoCarrinho.setProduto(ProdutoDAO.buscaProduto(rs.getInt("cod_produto")));
		produtoCarrinho.setQuantidade(rs.getInt("quantidade"));
		produtoCarrinho.setValor(rs.getDouble("valor"));
		carrinho.add(produtoCarrinho);
		}

		rs.close();
		stmt.close();
		con.close();
		} catch (SQLException e) {

		e.printStackTrace();
		}
	
		if(carrinho.isEmpty())

			return null;

		else
			return carrinho;
		}
	public static void excluirProdutoCarrinho(int cod, int codCliente){

		String sql = null;

		Connection con = GerenciadorConexao.getConnetion();

		PreparedStatement stmt = null;

		try{

		int quantidadeProdutoBD = verificaProdutoCarrinho(cod, codCliente);

		if(quantidadeProdutoBD == 1){			

		sql = "delete from carrinhocompras where cod_produto=? and cod_cliente=?";

		stmt = con.prepareStatement(sql);
		stmt.setInt(1,cod);
		stmt.setInt(2,codCliente);
		}
		else{
			sql = "update carrinhocompras set quantidade=?, valor=? where cod_produto=? and cod_cliente=?";
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,quantidadeProdutoBD-1);
			stmt.setDouble(2, ProdutoDAO.buscaProduto(cod).getValor()*(quantidadeProdutoBD-1));
			stmt.setInt(3,cod);
			stmt.setInt(4, codCliente);
		}

		stmt.execute();
		stmt.close();
		con.close();
		} catch (SQLException e) {

		
		throw new RuntimeException(e);

		}
	}	
}
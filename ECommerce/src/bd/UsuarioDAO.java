package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelo.Usuario;

public class UsuarioDAO {
	
	public static Usuario getCliente(int cod){
		
		Usuario usuario = null;
		Connection con = GerenciadorConexao.getConnetion();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from usuario where cod=? and papelusuario='cliente'");
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			usuario = new Usuario();
			usuario.setCod(rs.getInt("cod"));
			usuario.setLoginUsuario(rs.getString("loginusuario"));
			usuario.setNomeUsuario(rs.getString("nomeusuario"));
			usuario.setPapelUsuario(rs.getString("papelusuario"));
			usuario.setSenhaUsuario(rs.getString("senhausuario"));
			
			
			stmt.close();
			con.close();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuario;
		
	}

}

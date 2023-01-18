package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelo.Usuario;

public class LoginDAO {

	public static Usuario loginAdministrador(Usuario usuario){
		
		String sql = "select * from usuario where loginusuario=? and papelusuario=?";
		
		PreparedStatement ps = null;
		Connection con = GerenciadorConexao.getConnetion();
		Usuario usuarioBD = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getLoginUsuario());
			ps.setString(2, usuario.papelAdministrador);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				usuarioBD = new Usuario();
				usuarioBD.setCod(rs.getInt("cod"));
				usuarioBD.setLoginUsuario(rs.getString("loginusuario"));
				usuarioBD.setNomeUsuario(rs.getString("nomeusuario"));
				usuarioBD.setPapelUsuario(rs.getString("papelusuario"));
				usuarioBD.setSenhaUsuario(rs.getString("senhausuario"));
			}
			
			ps.close();
			con.close();
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(usuarioBD != null){
			if(usuario.getLoginUsuario().equals(usuarioBD.getLoginUsuario())
					&& usuario.getSenhaUsuario().equals(usuarioBD.getSenhaUsuario())){
				usuarioBD.setLogado(true);
			}
			else{
				usuarioBD.setLogado(false);
			}
			}
			return usuarioBD;
		}
public static Usuario loginCliente(Usuario usuario){
		
		String sql = "select * from usuario where loginusuario=? and papelusuario=?";
		
		PreparedStatement ps = null;
		Connection con = GerenciadorConexao.getConnetion();
		Usuario usuarioBD = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getLoginUsuario());
			ps.setString(2, usuario.papelCliente);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				usuarioBD = new Usuario();
				usuarioBD.setCod(rs.getInt("cod"));
				usuarioBD.setLoginUsuario(rs.getString("loginusuario"));
				usuarioBD.setNomeUsuario(rs.getString("nomeusuario"));
				usuarioBD.setPapelUsuario(rs.getString("papelusuario"));
				usuarioBD.setSenhaUsuario(rs.getString("senhausuario"));
			}
			
			ps.close();
			con.close();
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(usuarioBD != null){
			if(usuario.getLoginUsuario().equals(usuarioBD.getLoginUsuario())
					&& usuario.getSenhaUsuario().equals(usuarioBD.getSenhaUsuario())){
				usuarioBD.setLogado(true);
			}
			else{
				usuarioBD.setLogado(false);
			}
			}
			return usuarioBD;
		}
	}
	


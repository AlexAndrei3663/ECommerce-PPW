package bd;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class GerenciadorConexao {
	public static Connection getConnetion(){
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ECommerce", "postgres", "123456789");
			System.out.println("Conexão fechada" + conn.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
}  
return conn;
}

}
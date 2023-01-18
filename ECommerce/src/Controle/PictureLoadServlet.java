package Controle;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.ProdutoDAO;
import Modelo.Produto;

/**
 * Servlet implementation class Horas
 */

public class PictureLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int codProduto = Integer.parseInt(request.getParameter("codProduto"));
		
		Produto p = ProdutoDAO.buscaProduto(codProduto);
		
		ServletOutputStream out = response.getOutputStream();      
	            
	     byte[] buffer = new byte[ p.getImagem().getTamanhoImagem()];       
	     int bytesread = 0;    
	     while((bytesread = p.getImagem().getImagem().read(buffer))!=-1){    
	         out.write(buffer,0,bytesread);    
	     }    
	     out.flush();    
	     out.close();    
	     p.getImagem().getImagem().close();  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		 
	}

}

package Controle;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Produto;
import bd.ProdutoDAO;
import bd.SubCategoriaDAO;

/**
 * Servlet implementation class ServletAlterarProduto
 */
public class ServletAlterarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAlterarProduto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Produto pAlterar = new Produto();
		
		pAlterar.setNome(request.getParameter("nomeProduto"));
		pAlterar.setValor(Double.parseDouble(request.getParameter("valorProduto")));
		pAlterar.setCod(Integer.parseInt(request.getParameter("cod")));
		pAlterar.setSubCategoria(SubCategoriaDAO.buscaSubCategoria(Integer.parseInt(request.getParameter("caixaSubCategoria"))));
		
		ProdutoDAO.alterarProduto(pAlterar);
		
		RequestDispatcher view = request.getRequestDispatcher("/administrador/ListarProdutoAdministrador.jsp");
		view.forward(request, response);

	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

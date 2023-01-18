package Controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.SubCategoriaDAO;

/**
 * Servlet implementation class ServletCadastrarSubCategoria
 */
public class ServletCadastrarSubCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCadastrarSubCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codCategoria = Integer.parseInt(request.getParameter("caixaCategoria"));
		String nomeSubCategoria = request.getParameter("subcategoria");
		
		SubCategoriaDAO.adicionaSubCategoria(codCategoria, nomeSubCategoria);
		
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

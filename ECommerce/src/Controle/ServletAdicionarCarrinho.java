package Controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bd.CarrinhoDeComprasDAO;
import Modelo.Usuario;

/**
 * Servlet implementation class ServletAdicionarCarrinho
 */
public class ServletAdicionarCarrinho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdicionarCarrinho() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		
		int cod = Integer.parseInt(request.getParameter("cod"));
		
		CarrinhoDeComprasDAO.adicionaProdutoCarrinho(cod, usuario.getCod());
		
		RequestDispatcher view = request.getRequestDispatcher("/cliente/ListarProdutoCarrinho.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

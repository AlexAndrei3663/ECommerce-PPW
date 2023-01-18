package Controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Usuario;

/**
 * Servlet implementation class ServletLoginCliente
 */
public class ServletLoginCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLoginCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("username");
		String passwd = request.getParameter("password");
		
		Usuario user = new Usuario();
		
		user.setLoginUsuario(name);
		user.setPapelUsuario("cliente");
		user.setSenhaUsuario(passwd);
		user = bd.LoginDAO.loginCliente(user);
		
		if(user != null && user.isLogado()){
			request.getSession().setAttribute("usuario", user);
			RequestDispatcher view = request.getRequestDispatcher("/cliente/ListarProdutoCliente.jsp");
			view.forward(request, response);
		}
		else{
			request.setAttribute("msg", "Usuario ou senha inválidos");
			RequestDispatcher view = request.getRequestDispatcher("LoginCliente.jsp");
			view.forward(request, response);
		}
	}

}

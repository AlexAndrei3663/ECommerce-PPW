package Controle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Produto;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bd.ProdutoDAO;
import bd.SubCategoriaDAO;

/**
 * Servlet implementation class ServletCadastrarProduto
 */
public class ServletCadastrarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletCadastrarProduto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Produto produto = new Produto();

		// boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig()
				.getServletContext();
		File repository = (File) servletContext
				.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> items = null;
		// Parse the request
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Process the uploaded items
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();

			if (item.isFormField()) {
				verificaCampos(item, produto);
			} else {
				InputStream is = item.getInputStream();
				produto.getImagem().setTamanhoImagem((int) item.getSize());
				produto.getImagem().setImagem(is);
			}
		}

		ProdutoDAO.adicionaProduto(produto);

		RequestDispatcher view = request
				.getRequestDispatcher("/administrador/ListarProdutoAdministrador.jsp");
		view.forward(request, response);
	}

	private void verificaCampos(FileItem item, Produto produto) {

		switch (item.getFieldName()) {
		case "nomeProduto":
			produto.setNome(item.getString());
			break;
		case "valorProduto":
			produto.setValor(Double.parseDouble(item.getString()));
			break;
		case "caixaSubCategoria":
			int codSbCategoria = (Integer.parseInt(item.getString()));
			produto.setSubCategoria(SubCategoriaDAO
					.buscaSubCategoria(codSbCategoria));
			break;

		default:
			break;
		}
	}
}
	



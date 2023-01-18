
<%@page import="bd.SubCategoriaDAO"%>
<%@page import="Modelo.SubCategoria"%>
<%@page import="Modelo.Produto"%>
<%@page import="Modelo.Categoria"%>
<%@page import="java.util.*"%>
<%@page import="bd.ProdutoDAO"%>
<%@page import="bd.CategoriaDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="estilo.css">
<title>Lista de Produtos</title>

</head>
<body>
<h1>Lista de Produtos</h1>
<ul>
<% List<Categoria> listaDeCategorias = CategoriaDAO.buscaCategorias(); 
	Iterator<Categoria> ic = listaDeCategorias.iterator();
	while(ic.hasNext()){
		
		Categoria c = ic.next();
		%>
		<li><a href="/ECommerce/ListarProduto.jsp?codCategoria=<%=c.getCodCategoria()%>"><%=c.getNome()%></a>
   <%
   List<SubCategoria> ldsc = SubCategoriaDAO.buscaSubCategorias(c.getCodCategoria());
	   if(ldsc != null){
	   	Iterator<SubCategoria> ls = ldsc.iterator();
	    	while(ls.hasNext()){
		    	SubCategoria sc = ls.next();
		    	%>
		    	<ul>
				  		<li><a href="/ECommerce/ListarProduto.jsp?codSubCategoria=<%=sc.getCodSubCategoria()%>"><%=sc.getNome()%></a></li>
			   </ul>
			   <%
	    	}
	   }
	}
   %>
   		  
		 	 
		  </li>
	</ul>
		<br>


<%
		String sCategoria = request.getParameter("codCategoria");
		String sSubCategoria = request.getParameter("codSubCategoria");
		List<Produto> listaDeProdutos = null;
		
		if(sCategoria != null){
			int codCategoria = Integer.parseInt(sCategoria);
			listaDeProdutos = ProdutoDAO.buscaProdutosPorCategoria(codCategoria);
		}
		else if(sSubCategoria != null){
			int codSubCategoria = Integer.parseInt(sSubCategoria);
			listaDeProdutos = ProdutoDAO.buscaProdutoPorSubCategoria(codSubCategoria);
		}
		else{
			listaDeProdutos = ProdutoDAO.listaProdutos();
		}
		 



%><%if(listaDeProdutos != null){ %>
	<div id="divprodutos">
	<table border="1">
		<tr>
		<td>Código</td>
		<td>Nome</td>
		<td>Valor</td>
		
		</tr>
		
		<%
				Iterator<Produto> ip = listaDeProdutos.iterator();
				while(ip.hasNext()){
					
					Produto p = ip.next();
					
					%>
						<tr>
							<td><%= p.getCod() %></td>
							<td><%= p.getNome() %></td>
							<td><%= p.getValor() %></td>

						</tr>
						
				
				<% }}else{%>			
				         <td> <h1>Não há produtos no banco!</h1></td><br />
				         
				         
					<% }%>
		
		
		
		
	</table>
	</div>
	<br /> <br /> <br />
<input type="button" name="admin" value="Administrador" onclick="location.href='LoginAdministrador.jsp'"> &nbsp;&nbsp; <input type="button" name="client" value="Cliente" onclick="location.href='LoginCliente.jsp'">
</body>
</html>
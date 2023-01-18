<%@page import="Modelo.Produto"%>
<%@page import="java.util.*"%>
<%@page import="bd.ProdutoDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Produtos Administrador</title>
</head>
<body>

<h1>Administrador</h1><hr>
<ul>
		  <li><a href="">Categoria</a>
		 	 <ul>
		  		<li><a href="">SubCategorias</a></li>
		 	 </ul>
		  </li>
	</ul>
<%
		List<Produto> listaDeProdutos = ProdutoDAO.listaProdutos();



%><%if(listaDeProdutos != null){ %>
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
						
							<td><img width="200px" height="200px" src="/ECommerce/administrador/carregaFoto?codProduto=<%= p.getCod()%>"/></td>
							<td><%= p.getCod() %></td>
							<td><%= p.getNome() %></td>
							<td><%= p.getValor() %></td>
							<td><a href="/ECommerce/administrador/AlterarProduto.jsp?cod=<%= p.getCod() %>&codCategoria=<%= p.getSubCategoria().getCategoria().getCodCategoria() %>&codSubCategoria=<%= p.getSubCategoria().getCodSubCategoria() %>">Alterar</a></td>
							<td><a href="/ECommerce/administrador/excluirProduto?cod=<%= p.getCod() %>">Excluir</a></td>
						</tr>
				
				<%}}else{%>			
		         <td> <h1>Não há produtos no banco!</h1></td>
		         
			<% }%>
		
		
		
		
	</table>
	<br /> <br /> <br />
	<a href="/ECommerce/administrador/CadastrarProduto.jsp">Cadastrar Novo Produto</a><br /><br />
	<a href="/ECommerce/administrador/CadastrarCategoria.html">Cadastrar Nova Categoria</a><br /><br />
	<a href="/ECommerce/administrador/CadastrarSubCategoria.jsp">Cadastrar Nova SubCategoria</a><br /><br />
	
	<a href="/ECommerce/ListarProduto.jsp">Voltar a Página Inicial</a>


	
</body>
</html>
<%@page import="Modelo.Produto"%>
<%@page import="java.util.*"%>
<%@page import="bd.ProdutoDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Produtos Cliente</title>
</head>
<body>

<h1>Cliente</h1>
<hr>
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
	
	<table border="1" style="float: left; margin: -1% 25% 0% 3%">
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
							<td><a href="/ECommerce/cliente/adicionaCarrinho?cod=<%=p.getCod()%>">Adicionar ao Carrinho</a></td>
							
						</tr>
				
				<%}}else{%>			
		         <td> <h1>Não há produtos no banco!</h1></td>
		        
		         
			<% }%>
	
	
	<br /> <br /> <br />
	 
	
</body>
</html>
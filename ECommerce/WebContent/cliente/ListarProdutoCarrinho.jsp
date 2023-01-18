<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.CarrinhoDeCompras"%>
<%@page import="bd.CarrinhoDeComprasDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Produto"%>
<%@page import="java.util.List"%>
<%@page import="bd.ProdutoDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Produtos</title>
</head>
<body>
Carrinho de Compras
<%
	Usuario usuario = (Usuario)session.getAttribute("usuario");
	List<CarrinhoDeCompras> listaDeProdutosCarrinho = CarrinhoDeComprasDAO.pegaProdutosCarrinho(usuario.getCod());
	if(listaDeProdutosCarrinho!= null){
%>

	<table>
		<tr>
			<td>Código</td>
			<td>Nome</td>
			<td>Quantidade</td>
			<td>Valor</td>
		</tr>
		
		<%
		
			Iterator<CarrinhoDeCompras> ip = listaDeProdutosCarrinho.iterator();
			while(ip.hasNext()){
				
				CarrinhoDeCompras p = ip.next();
				
				
				%>
				
				<tr>
					<td><%= p.getCod() %></td>
					<td><%= p.getProduto().getNome() %></td>
					<td><%= p.getQuantidade() %></td>
					<td><%= p.getValor() %></td>
					<td><a href="/ECommerce/cliente/excluirProdutoCarrinho?cod=<%= p.getProduto().getCod() %>">Excluir do carrinho</a></td>
					
				</tr>
				
				
			<%}%>
		
		
		
		
	</table>
	<%} else{ out.println("Sem produtos no carrinho");}%>
	<br>
	<br>
	<a href="/ECommerce/cliente/ListarProdutoCliente.jsp">Continuar Comprando</a>
	<br><a href="/ECommerce/cliente/finalizarSessao">Sair</a>
</body>
</html>
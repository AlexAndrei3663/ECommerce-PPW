<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="bd.CategoriaDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar SubCategoria</title>
</head>
<body>
<form method="get" action="/ECommerce/administrador/cadastrarSubCategoria">

	Categoria<br>
	<select name="caixaCategoria">
	
	<%
		List<Categoria> ls = CategoriaDAO.buscaCategorias();
		Iterator<Categoria> ic = ls.iterator();
		
		while(ic.hasNext()){
			Categoria c = ic.next();
			%>
			<option value="<%=c.getCodCategoria()%>"><%=c.getNome()%></option>
			<%
		}
	%>
				
	</select><br>

	SubCategoria<br>
	<input type="text" name="subcategoria">
	
	<input type="submit"  value="cadastrar">
</form>
</body>
</html>
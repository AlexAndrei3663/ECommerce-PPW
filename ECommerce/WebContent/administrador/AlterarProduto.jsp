<%@page import="bd.SubCategoriaDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.SubCategoria"%>
<%@page import="Modelo.Categoria"%>
<%@page import="bd.CategoriaDAO"%>
<%@page import="Modelo.Produto"%>
<%@page import="bd.ProdutoDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%

	Produto p = ProdutoDAO.buscaProduto(Integer.parseInt(request.getParameter("cod")));

%>
<script type="text/javascript">
	function selecionaCategoria(){
		var myParam = location.search.split('codCategoria=')[1].split('&')[0];
		document.getElementById("caixaCategoria").value = ""+myParam;
		
		var myParam1 = location.search.split('codSubCategoria=')[1];
		if(myParam1!= null)
			document.getElementById("caixaSubCategoria").value = ""+myParam1;
		
	}
     function carregaSubCategorias(obj){
           window.location.href="/ECommerce/administrador/AlterarProduto.jsp?cod=<%=p.getCod()%>&codCategoria="+obj.value;
           var c = document.getElementById("caixaCategoria");
           c.options[obj.value].selected = true;
     }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterar Produto</title>
</head>
<body onload="selecionaCategoria()">
<%
Categoria c= new Categoria();
SubCategoria sc= new SubCategoria();
int codCategoria = 0;
String cod = request.getParameter("codCategoria");
	if(cod!= null)
		codCategoria = Integer.parseInt(cod);
	else{
		codCategoria = CategoriaDAO.buscaCategorias().iterator().next().getCodCategoria();
	}
%>


<form method="get" action="/ECommerce/administrador/alterarProduto">

	Categoria<br>
	<select id="caixaCategoria" name="caixaCategoria"  onchange="carregaSubCategorias(this)">
	
		<%
			List<Categoria> ls = CategoriaDAO.buscaCategorias();
			Iterator<Categoria> ic = ls.iterator();
			
			while(ic.hasNext()){
				
				c = ic.next();

				%>
				<option value="<%=c.getCodCategoria()%>"><%=c.getNome() %></option>
				<%
			}
			
		%>
		
		
	</select>
	
	<br>SubCategoria<br>
	<select id="caixaSubCategoria" name="caixaSubCategoria">
	
		<%
			
			
			System.out.println("cod: "+codCategoria);
			List<SubCategoria> lsc = SubCategoriaDAO.buscaSubCategorias(codCategoria);
			if(lsc != null){
				Iterator<SubCategoria> isc = lsc.iterator();
				
				while(isc.hasNext()){
					sc = isc.next();
					%>
					<option value="<%=sc.getCodSubCategoria()%>"><%=sc.getNome() %></option>
					<%
				}
			}
			
		%>
		
		
	</select>
	
	<br>

	Nome do produto:
	<input name="nomeProduto" type="text" value="<%=p.getNome()%>"><br>
	Valor do produto:
	<input name="valorProduto" type="text" value="<%=p.getValor()%>"><br>
	<input name="cod" type="hidden" value="<%=p.getCod()%>">
	<input name="alterar" value="alterar" type="submit"><br>
</form>
</body>
</html>
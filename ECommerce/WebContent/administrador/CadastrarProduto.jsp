<%@page import="Modelo.SubCategoria"%>
<%@page import="bd.SubCategoriaDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="bd.CategoriaDAO"%>
<%@page import="Modelo.Categoria"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function selecionaCategoria(){
		
		var myParam = location.search.split('indexCategoria=')[1];
		var country = document.getElementById("caixaCategoria");
        country.options[myParam].selected = true;
		
	}
     function carregaSubCategorias(obj){
           window.location.href="/ECommerce/administrador/CadastrarProduto.jsp?codCategoria="+obj.value+"&indexCategoria="+obj.selectedIndex;
           var c = document.getElementById("caixaCategoria");
           c.options[obj.value].selected = true;
     }
</script>
<title>Insert title here</title>
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
<form enctype="multipart/form-data" method="POST" action="/ECommerce/administrador/cadastrarProduto">
	
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
	<select name="caixaSubCategoria">
	
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
	<input name="nomeProduto" type="text"><br>
	Valor do produto:
	<input name="valorProduto" type="text"><br>
	Selecionar foto:	<input type="file" name="foto"/> <br>
	<input name="cadastrar" value="cadastrar" type="submit"><br>
</form>
</body>
</html>
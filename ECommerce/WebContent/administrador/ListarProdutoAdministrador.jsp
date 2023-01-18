<%@page import="Modelo.Produto"%>
<%@page import="java.util.*"%>
<%@page import="bd.ProdutoDAO"%>
<%@page import="Modelo.SubCategoria"%>
<%@page import="bd.SubCategoriaDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="bd.CategoriaDAO"%>
<%@page import="Modelo.Categoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>EasyBuy</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="estilo.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
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
		<div id="main">
			<div id="top">
				<div id="logo"></div>
				<div id="busca">
					<div id="busca_esquerda"></div>
					<div id="busca_centro">
						<div id="label_buscar">Buscar</div>
						<div id="caixa_esquerda"></div>
						<div id="caixa_centro">
							<input type="text" id="caixa_busca" name="busca"/>
						</div>
						<div id="caixa_direita"></div>	
						<div id="botao_buscar"></div>
					</div>
					<div id="busca_direita"></div>			
				</div>
				<div id="botao_carrinho"></div>	
				<div id="menu_header">
					<a href="#" class="font_menu">Logar ou Cadastrar-se</a>
					<a href="#" class="font_menu">Meus Pedidos</a>
					<a href="#" class="font_menu">Atendimento</a>
				</div>	
			</div>
			<div id="corpo">
				<div id="banner"></div>
				<div id="categoria">
					<div id="categoriaEsquerda"></div>
					<div id="categoriaCentro">
						<div id="textoCategoria" class="font_titulo">Categotia</div>
					</div>
					<div id="categoriaDireita"></div>
					<div id="corpoCategoria">
						<ul id=ulCategoria>
						<%
							List<Categoria> ls = CategoriaDAO.buscaCategorias();
							Iterator<Categoria> ic = ls.iterator();
							
							while(ic.hasNext()){
								
								c = ic.next();
							%>
							<li class="liCategoria"><a class="fontCategoria" href="#"><%=c.getNome() %></a></li>
							<ul class="ulItensCategoria">
							<%
							List<SubCategoria> lsc = SubCategoriaDAO.buscaSubCategorias(codCategoria);
							if(lsc != null){
								Iterator<SubCategoria> isc = lsc.iterator();
								
								while(isc.hasNext()){
									sc = isc.next();
									%>
										<li class="liCategoria"><a class="fontItemMenu" href="#"><%=sc.getNome() %></a></li>
									
									<%
									}
								}	
							%>
							</ul>
							<%
								}
								
							%>
						</ul>
					</div>
				</div>
				<div id="produtos">
					<div id="produtosEsquerda"></div>
					<div id="produtosCentro">
						<div id="textoProdutos" class="font_titulo">Produtos em Destaque</div>
					</div>
					<div id="produtosDireita"></div>
					<div id="corpoProduto">
					<%
					List<Produto> listaDeProdutos = ProdutoDAO.listaProdutos();

					if(listaDeProdutos != null){ %>
					<ul>
						<%Iterator<Produto> ip = listaDeProdutos.iterator();
						while(ip.hasNext()){
						
							Produto p = ip.next();
					%>
						
							<li class="li_produto">
									<div class="div_produto">
										<div class="produtoImagem">
											<img width="200px" height="200px" src="/ECommerce/administrador/carregaFoto?codProduto=<%= p.getCod()%>"/>
											<img src="imagens/detalhes.png"/>
										</div>
										<p><%= p.getNome() %></p>
										<p><del>R$ <%= p.getValor()+((p.getValor()*20)/100)%></del> <b>R$ <%= p.getValor() %></b></p>
									</div>
							</li>
							<%}}else{%>			
		        			 <td> <h1>Não há produtos no banco!</h1></td>
		         
							<% }%>
						</ul>
						<br /> <br /> <br />
						<a href="/ECommerce/administrador/CadastrarProduto.jsp">Cadastrar Novo Produto</a><br /><br />
						<a href="/ECommerce/ListarProduto.jsp">Voltar a Página Inicial</a>
						<a href="/ECommerce/administrador/CadastrarCategoria.html">Cadastrar Categoria</a>
						<a href="/ECommerce/administrador/CadastrarSubCategoria.jsp">Cadastrar SubCategoria</a>
					</div>
				</div>
			</div>
			<div id="rodape"></div>
		</div>
	</body>
</html>

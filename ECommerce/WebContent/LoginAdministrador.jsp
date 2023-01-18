<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Administrador</title>
</head>
<body>
	<form method="post" action="/ECommerce/loginAdministrador">
	<h1>Administrador</h1><hr>
		Usuário:
		<input type="text" name="username">
		Senha:
		<input type="password" name="password">
		
		<input type="submit" name="login" value="login">
	</form>

</body>
</html>
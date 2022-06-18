<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Insert title here</title>
</head>
<body>

<!-- PANTALLA LOGIN 

INPUT PARA NOMBRE USUARIO Y CONTRASEÑA
BOTON ACEPTAR

-->
<div class="login">
	<h1>Login</h1>
    <form method="post" class="input">
    	<input type="text" name="txtUsername" placeholder="Username" required="required" />
        <input type="password" name="txtPass" placeholder="Password" required="required" />
        <button type="submit" class="btn btn-primary btn-block btn-large">INGRESAR</button>
    </form>
</div>

</body>
</html>
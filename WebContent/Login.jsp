<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page buffer="64kb" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Login</title>
</head>
<body>
<% 
HttpServletResponse res = (HttpServletResponse) response;
HttpSession sesion = ((HttpServletRequest) request).getSession();

	if(sesion.getAttribute("tipo")!=null){
		if(sesion.getAttribute("tipo").toString().equals("Admin")){
			res.sendRedirect("IndexAdmin.jsp");
			return;
		}
		else{
			res.sendRedirect("ServletIndexMedico?Index=1");
			return;
		}
	}
%>  
<!-- PANTALLA LOGIN 

INPUT PARA NOMBRE USUARIO Y CONTRASEŅA
BOTON ACEPTAR

-->
<div class="login">
	<h1>Login</h1>
    <form method="post" action ="login" >
    	<input type="text" class="input" name="username" placeholder="Username" required />
        <input type="password" class="input" name="pass" placeholder="Password" required />
        <button type="submit" class="btn btn-primary btn-block btn-large">INGRESAR</button>
        <div>
			<p>
				<%
					String resultado = (String)request.getAttribute("mensaje");
					String mensaje = "";
					if (resultado != null) {
						mensaje = resultado;
					}
				%>
				<%=mensaje %>
			</p>
		</div>
    </form>
</div>

</body>
</html>
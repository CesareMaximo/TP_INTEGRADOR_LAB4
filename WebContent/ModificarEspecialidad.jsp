<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidad.*"%>
<%@page import="NegocioImpl.*"%>
<%@ page buffer="64kb" %>
<%@page import="Negocio.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Especialidad</title>

<style type="text/css">
<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>


</head>

<% 	
				Especialidad es = new Especialidad();
				es = (Especialidad)session.getAttribute("especialidad");

				
%>

<body>
<% 
HttpServletResponse res = (HttpServletResponse) response;
HttpSession sesion = ((HttpServletRequest) request).getSession();

	if(sesion.getAttribute("username")==null){
		res.sendRedirect("Login.jsp");
	}
	if(sesion.getAttribute("tipo")!=null){
		if(sesion.getAttribute("tipo").toString().equals("Medico")){
			res.sendRedirect("Error.jsp");
			return;
	}
	}
%>  

<div style="float: left; margin-left: 12px; margin-top: 6px;">
		<a href="ServletEspecialidad?Param=1"><img src="img/atras.png"
			height="20px" /></a> <a href="IndexAdmin.jsp"> <img
			src="img/home.png" height="20px" style="margin-left: 10px;"
			width="20px"></a>
	</div>
	
	<form method="post" action="logout">
		<div
			style="font-family: Open Sans; margin-top: 6px; float: right; margin-right: 12px; color: #fff; font-size: 12px;">
			 <a href="MiCuenta.jsp" title="Mi Cuenta" data-toggle="tooltip""><img width="16px"class="imag" src="img/user.png"/></a> Bienvenido <b> <%= session.getAttribute("username") %></b>
			<input name="cerrarSesion" type="submit" value="Cerrar Sesi?n"
				class="btn btn-primary btn-sm" style="margin-left: 10px;">
		</div>
		<br>
	</form>

	<div class="registro">
		<form method="post" action="ServletEspecialidad">
			<h1>Modificar Especialidad</h1>
			
			<table class="formulario">
			<tr><td><label>ID</label></td><td><input name="txtIdEspecialidad" type="text" class="inputForm" readonly size="20" required value="${especialidad.idEspecialidad}" ></td></tr>
			<tr><td><label>Descripcion</label></td><td><input name="txtDescripcion" oninput="this.value = this.value.replace(/[^a-zA-Z?????????? ]/,'')" placerholder="Ingrese la Descripci?n" type="text" class="inputForm" size="20" required value="${especialidad.descripcion}" ></td></tr>
			
			 </table>
			
			<input name="btnModificarEspecialidad" type="submit" value="Aceptar"	class="btn btn-primary btn-block btn-large">
		</form>
		<br>
		<div>
				<p style="color: red; margin-left: 125px;">
					<%
						String resultado = (String) request.getAttribute("mensaje");
								String mensaje = "";
								if (resultado != null) {
									mensaje = resultado;
								}
					%>
					<%=mensaje%>
				</p>

			</div>
	</div>



</body>
</html>
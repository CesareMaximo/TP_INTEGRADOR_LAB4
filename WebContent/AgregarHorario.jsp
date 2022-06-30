<%@page import="Entidad.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Horario</title>
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
</head>
<body>
	<% 
    Medico me = (Medico)session.getAttribute("medico");
    %>
	<div style="float: left; margin-left: 12px; margin-top: 6px;">
		<a href="ServletMedico?Param=1"><img src="img/atras.png"
			height="20px" /></a> <a href="IndexAdmin.jsp"> <img
			src="img/home.png" height="20px" style="margin-left: 10px;"
			width="20px"></a>
	</div>
	<form method="post" action="logout">
		<div
			style="font-family: Open Sans; margin-top: 6px; float: right; margin-right: 12px; color: #fff; font-size: 12px;">
			<img width="16px" class="imag" src="img/user.png" /> Bienvenido <b><%=session.getAttribute("username")%></b>
			<input name="cerrarSesion" type="submit" value="Cerrar Sesión"
				class="btn btn-primary btn-sm" style="margin-left: 10px;">
		</div>
		<br>
	</form>

	<div class="registro">
		<form method="post" action="Horario">
			<h1>Registro de Nuevo Horario</h1>
			<h2>DR/A.: <%=me.getNombre()%> <%=me.getApellido() %></h2>
			<table class="formulario">
				<tr>
					<td class=top><label>Dia de atenci&oacuten:</label></td>
					<td>
						<select class="select">
							<option>Lunes</option>
							<option>Martes</option>
							<option>Miercoles</option>
							<option>Jueves</option>
							<option>Viernes</option>
							<option>Sabado</option>
						</select>
				<tr>
					<td class="top"><label>Horario de Atenci&oacuten:</label></td>
					<td><select class="select"><option>08:00</option>
							<option>09:00</option>
							<option>10:00</option>
							<option>11:00</option>
							<option>12:00</option>
							<option>13:00</option>
							<option>14:00</option>
							<option>15:00</option>
							<option>16:00</option>
							<option>17:00</option>
							<option>18:00</option>
							<option>19:00</option>
					</select><label class="lbl"> Ingreso </label><br> <select class="select">
							<option>09:00</option>
							<option>11:00</option>
							<option>12:00</option>
							<option>13:00</option>
							<option>14:00</option>
							<option>15:00</option>
							<option>16:00</option>
							<option>17:00</option>
							<option>18:00</option>
							<option>19:00</option>
							<option>20:00</option>
					</select><label class="lbl"> Salida </label></td>
				</tr>
			</table>
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
			<input name="btnNuevoHorario" type="submit" value="Aceptar"	class="btn btn-primary btn-block btn-large">
		</form>
	</div>

</body>
</html>
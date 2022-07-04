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
			<a href="MiCuenta.jsp" title="Mi Cuenta" data-toggle="tooltip""><img width="16px"class="imag" src="img/user.png"/></a> Bienvenido <b> <%= session.getAttribute("username") %></b>
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
						<select class="select" name="slcDia">
							<option value=2>Lunes</option>
							<option value=3>Martes</option>
							<option value=4>Miercoles</option>
							<option value=5>Jueves</option>
							<option value=6>Viernes</option>
							<option value=7>Sabado</option>
						</select>
				<tr>
					<td class="top"><label>Horario de Atenci&oacuten:</label></td>
					<td><select class="select" name="slcIngreso">
							<option>08:00:00</option>
							<option>09:00:00</option>
							<option>10:00:00</option>
							<option>11:00:00</option>
							<option>12:00:00</option>
							<option>13:00:00</option>
							<option>14:00:00</option>
							<option>15:00:00</option>
							<option>16:00:00</option>
							<option>17:00:00</option>
							<option>18:00:00</option>
							<option>19:00:00</option>
					</select><label class="lbl"> Ingreso </label><br> <select class="select" name="slcEgreso">
							<option>09:00:00</option>
							<option>11:00:00</option>
							<option>12:00:00</option>
							<option>13:00:00</option>
							<option>14:00:00</option>
							<option>15:00:00</option>
							<option>16:00:00</option>
							<option>17:00:00</option>
							<option>18:00:00</option>
							<option>19:00:00</option>
							<option>20:00:00</option>
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
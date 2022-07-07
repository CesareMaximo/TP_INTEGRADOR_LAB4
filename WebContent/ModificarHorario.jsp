<%@page import="Entidad.*"%>
<%@ page buffer="64kb" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Horario</title>
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
</head>
<body>
<% 
HttpServletResponse res = (HttpServletResponse) response;
HttpSession sesion = ((HttpServletRequest) request).getSession();

	if(sesion.getAttribute("username")==null){
		res.sendRedirect("Login.jsp");
		return;
	}
	if(sesion.getAttribute("tipo")!=null){
		if(sesion.getAttribute("tipo").toString().equals("Medico")){
			res.sendRedirect("Error.jsp");
			return;
	}
	}
	Medico me = new Medico();
	if (session.getAttribute("medico") != null){
    me = (Medico)session.getAttribute("medico");		
	}
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
			<h1>Modificar Horario</h1>
			<h2>DR/A.: <%=me.getNombre()%> <%=me.getApellido() %></h2> 
			<table class="formulario">
				<tr>
					<td class=top><label>Dia de atenci&oacuten:</label></td>
					
					<%
					String dia="";
					if(request.getParameter("diaAtencion") != null){
						dia = request.getParameter("diaAtencion");
						
					}%>
					<td><input readonly class="label" name="lbldia" value="<%=dia%>"/>
					<%
					String iDdia="";
					if(request.getParameter("idDiaAtencion") != null){
						iDdia = request.getParameter("idDiaAtencion");
						
					}%>
					<input type="hidden" name="idDia" value=<%=iDdia%>>  
					</td>
				<tr>
						
					<td class="top"><label>Horario de Atenci&oacuten:</label></td>
					<td><select class="select" name="slcIngreso">
					<%
					
					String horariosIng[];
					horariosIng = new String[12];
					horariosIng[0]="08:00:00";
					horariosIng[1]="09:00:00";
					horariosIng[2]="10:00:00";
					horariosIng[3]="11:00:00";
					horariosIng[4]="12:00:00";
					horariosIng[5]="13:00:00";
					horariosIng[6]="14:00:00";
					horariosIng[7]="15:00:00";
					horariosIng[8]="16:00:00";
					horariosIng[9]="17:00:00";
					horariosIng[10]="18:00:00";
					horariosIng[11]="19:00:00";
					
					String ing="";
					if(request.getParameter("horaIngreso") != null){
						ing = request.getParameter("horaIngreso");
						
					}
					
					for(int i=0; i < horariosIng.length; i++){
						
						if(horariosIng[i].equals(ing)){
							%><option selected><%=horariosIng[i]%></option>
						<% }else{%>
							<option><%=horariosIng[i]%></option>		
						<%} 
					}
					
					%>	
							
					</select><label class="lbl"> Ingreso </label><br>
					 <select class="select" name="slcEgreso">
								<%
							String eg="";
							if(request.getParameter("horaEgreso") != null){
								eg = request.getParameter("horaEgreso");
								
							}
							
							
							String horariosEg[];
							horariosEg = new String[12];
							horariosEg[0]="09:00:00";
							horariosEg[1]="10:00:00";
							horariosEg[2]="11:00:00";
							horariosEg[3]="12:00:00";
							horariosEg[4]="13:00:00";
							horariosEg[5]="14:00:00";
							horariosEg[6]="15:00:00";
							horariosEg[7]="16:00:00";
							horariosEg[8]="17:00:00";
							horariosEg[9]="18:00:00";
							horariosEg[10]="19:00:00";
							horariosEg[11]="20:00:00";
						
							for(int i=0; i < horariosIng.length; i++){
						
						if(horariosEg[i].equals(eg)){
							%><option selected><%=horariosEg[i]%></option>
						<% }else{%>
							<option><%=horariosEg[i]%></option>		
						<%} 
					}
					
					%>	
							
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
			<input name="btnModificarHorario" type="submit" value="Aceptar"	class="btn btn-primary btn-block btn-large">
		</form>
	</div>

</body>
</html>
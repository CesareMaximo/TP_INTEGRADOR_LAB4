<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.css">

<title>Menu Reportes</title>


 

</head>
<body>
<!-- 
MENU 
OPCIONES:
TOTAL DE PACIENTES ATENDIDOS POR MEDICO 
TOTAL DE TURNOS AUSENTES POR ENTRE FECHA Y FECHA
PROMEDIO DE PACIENTES POR MEDICO
PROMEDIO DE PACIENTES POR ESPECIALIDAD
 -->

 
<div style="float: left; margin-left: 12px; margin-top:6px;">
<a href="IndexAdmin.jsp"><img src="img/atras.png" height="20px" /></a>
<a href="IndexAdmin.jsp"> <img src="img/home.png" height="20px" style="margin-left:10px;" width="20px" ></a> 
</div>
<form method="post" action ="logout" >
 <div  style=" font-family:Open Sans; margin-top:6px; float: right; margin-right: 12px; color: #fff; font-size: 12px; ">
 <a href="MiCuenta.jsp" title="Mi Cuenta" data-toggle="tooltip""><img width="16px"class="imag" src="img/user.png"/></a> Bienvenido <b> <%= session.getAttribute("username") %></b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary btn-sm" style="margin-left:10px;"></div>
	<br>
</form>	
	<div class="registro">
		<form>
			<h1>Reportes</h1>
			
			 <div style="display: flex; flex-direction: row; flex-wrap: wrap">
                        <a href="Reportes?Param=1" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 11rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:50px;">
                                <img src="img/Reportes.png" style="width: 40px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-textR" >Total de pacientes atendidos por medico</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="Reportes?Modal2=1" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 11rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:80px;">
                                <img src="img/Reportes.png" style="width: 40px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-textR" >Total turnos ausente entre fechas</p>
                                </div>
                            </div>
                        </a>
                        
                       
                       <a href="Reportes?Modal3=1" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 11rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:50px;">
                                <img src="img/Reportes.png" style="width: 40px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-textR" >Cantidad turnos atendido por mes y año</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="#openModalAnio" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 11rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:80px;">
                                <img src="img/Reportes.png" style="width: 40px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-textR" >Porcentaje de asistencia por año</p>
                                </div>
                            </div>
                        </a>
                        
                      
                        
                        
			</div>
			</form>
	</div>


<!-- MODAL TOTAL PACIENTES ATENDIDOS POR MÉDICO -->


	<%
		boolean exito = false;
		String texto = "";

		if (request.getAttribute("exito") != null) {

		exito = (boolean) request.getAttribute("exito");
		texto = "registrado";

		}
		if (exito == true) {
	%>
	<script type="text/javascript">
		window.onload = function() {
			OpenBootstrapPopup();
		};
		function OpenBootstrapPopup() {
			$("#simpleModal").modal('show');
		}
	</script>

	<form action="Reportes" method="post">
		<div id="simpleModal" class="modal fade">
			<div class="modal-dialog modal-det">
				<div class="modal-content">
					<div class="modal-header justify-content-left">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<h4>Ingrese las fechas:</h4>
						
						<table class="tbModal">
						<tr>
						<% if(request.getAttribute("totalPaciente") != null){ 
						%>	
						<td><input type="date" name="Fecha1" required value="<%=request.getAttribute("fecha1")%>" class="inputForm"></input></td>
						<td><label>Hasta:</label></td>
						<td><input type="date" name="Fecha2"   required  value="<%=request.getAttribute("fecha2")%>"  class="inputForm" ></input><td>
						<%
						}else{
						%>
						<td><input type="date" name="Fecha1"  required  class="inputForm"></input></td>
						<td><label>Hasta:</label></td>
						<td><input type="date" name="Fecha2"   required class="inputForm" ></input><td>
						
						<%} %>
						</tr>
						</table>
						
						
						<h5>Seleccione el Medico:</h5>
						
						<table class="tbModal">
						<tr>
						<td><label>Medico:</label></td>
						<td><select class="select" name="medicoReporte">
							<option selected="true" value="0" >Selecciona una opción</option>
							<%
							int total;
							Medico medico = new Medico();
							ArrayList<Medico> listaMedico = null;
									if (request.getAttribute("listaMedico") != null) {
										listaMedico = (ArrayList<Medico>) request.getAttribute("listaMedico");
									}
									if (listaMedico != null)
										for (Medico me : listaMedico) {
							%>
							<option value="<%=me.getIdMedico().getIdUsuario()%>">
								<%=me.getNombre()%>
								<%=me.getApellido()%>
							</option>
							<%
								}
							%>
						</select> </input></td>
						<td><input name="totalPacientes" type="submit" value="Buscar" class="btn btn-primary" style="margin-left: 20px;"></input></td>
						</tr>
						</table>

						<div>
							<p style="color:red; margin-top:15px" >
								<%
								
										String mensaje = "";
										if (request.getAttribute("advertencia") != null) {
											mensaje = request.getAttribute("advertencia").toString();
										}
								%>
								<%=mensaje%>
							</p>
						</div>



						<%if(request.getAttribute("totalPaciente") != null && request.getAttribute("medico") != null){
							total = (int)request.getAttribute("totalPaciente");
							medico = (Medico)request.getAttribute("medico");
							%>
						<table class="tbResultados">
						<tr>
						<th>
						Médico
						</th>
						<th>
						Total pacientes atendidos
						</th>
						</tr>
						<tr>
						<td><%=medico.getNombre()%> <%=medico.getApellido()%></td>
						<td><%=total%></td>
						</tr>
						</table>
						<% }else{
							%><label> </label><%}%>
							
					</div>
				</div>
			</div>
		</div>
	</form>
	<%
		}
	%>
	


<!-- MODAL TOTAL TURNOS AUSENTE-->
<%
		boolean exito2 = false;

		if (request.getAttribute("exito2") != null) {

		exito2 = (boolean) request.getAttribute("exito2");
		}
		if (exito2 == true) {
	%>
	<script type="text/javascript">
		window.onload = function() {
			OpenBootstrapPopup();
		};
		function OpenBootstrapPopup() {
			$("#totalAusentes").modal('show');
		}
	</script>

	<form action="Reportes" method="post">
		<div id="totalAusentes" class="modal fade">
			<div class="modal-dialog modal-det">
				<div class="modal-content">
					<div class="modal-header justify-content-left">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<h4>Ingrese las fechas:</h4>
						
						<table class="tbModal">
						<tr>
						<td><label>De:</label></td>
						
						<% if(request.getAttribute("totalAusentes") != null){ 
						%>	
						<td><input type="date" name="Fecha1" required value="<%=request.getAttribute("fecha1")%>" class="inputForm"></input></td>
						<td><label>Hasta:</label></td>
						<td><input type="date" name="Fecha2" required value="<%=request.getAttribute("fecha2")%>"  class="inputForm" ></input><td>
						<%
						}else{
						%>
						<td><input type="date" required name="Fecha1" class="inputForm"></input></td>
						<td><label>Hasta:</label></td>
						<td><input type="date" required name="Fecha2" class="inputForm" ></input><td>
						
						<%} %>
						<td> <input name="btnTotalAusentes" type="submit" value="Buscar" class="btn btn-primary" style="margin-left: -5px; margin-top:-5px;"></input> </td>
						</tr>
						</table>				
						<%
						int totalAusentes=0;
						if (request.getAttribute("totalAusentes") != null){
							totalAusentes = (int)request.getAttribute("totalAusentes");  %>
							
						<table class="tbResultados">
						<tr>
						<th>
						Total Turnos Ausentes :
						</th>
						<th>
						<%=totalAusentes%>
						</th>
						</tr>
						</table>
						<% } %>

						
							
					</div>
				</div>
			</div>
		</div>
	</form>
	<%
		}
	%>


<!-- MODAL CANTIDAD TURNOS ATENDIDOS POR MES Y AÑO -->

<%
		boolean exito3 = false;

		if (request.getAttribute("exito3") != null) {

		exito3 = (boolean) request.getAttribute("exito3");
		}
		if (exito3 == true) {
	%>
	<script type="text/javascript">
		window.onload = function() {
			OpenBootstrapPopup();
		};
		function OpenBootstrapPopup() {
			$("#totalAtendidos").modal('show');
		}
	</script>

	<form action="Reportes" method="post">
		<div id="totalAtendidos" class="modal fade">
			<div class="modal-dialog modal-det">
				<div class="modal-content">
					<div class="modal-header justify-content-left">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<h4>Ingrese las fechas:</h4>
						
						<table class="tbModal">
						<tr>
						<td><label>Mes:</label></td>
						<td><select name="slcMes" type="select" class="select"> 
						<option value=1>Enero</option>
						<option value=2>Febrero</option>
						<option value=3>Marzo</option>
						<option value=4>Abril</option>
						<option value=5>Mayo</option>
						<option value=6>Junio</option>
						<option value=7>Julio</option>
						<option value=8>Agosto</option>
						<option value=9>Septiembre</option>
						<option value=10>Octubre</option>
						<option value=11>Noviembre</option>
						<option value=12>Diciembre</option>
						</select></td>
						<td><label>Año:</label></td>
						<td><select name="slcAnio" type="select" class="select">
						<%for(int i=2015; i <= 2022 ; i = i + 1){ %>
						<option><%=i%></option>
						<%}%>				
						</select></td>
						
						<td> <input name="btnTotalAtendidos" type="submit" value="Buscar" class="btn btn-primary" style="margin-left: 10px; margin-top:-5px;"></input> </td>
						</tr>
						</table>				
						<%
						int totalAtendidos=0;
						if (request.getAttribute("totalAtendidos") != null){
							totalAtendidos = (int)request.getAttribute("totalAtendidos");  %>
							
						<table class="tbResultados">
						<tr>
						<th>
						Cantidad de turnos atendidos en <%=request.getAttribute("mes")%>/<%=request.getAttribute("anio")%>:
						</th>
						<th>
						<%=totalAtendidos%>
						</th>
						</tr>
						</table>
						<% } %>

						
							
					</div>
				</div>
			</div>
		</div>
	</form>
	<%
		}
	%>














<div id="openModalMes" class="modalDialog">
	<div>
		<a href="#close" title="Close" class="close">X</a>
		<h2>Ingrese el mes y el año:</h2>
		<label> Fecha:</label><input type="month" name="Fecha1" class="inputForm"></input>
	
		<input  name="buscarMes" type="submit" value="Buscar" class="btn btn-primary" style="margin-left:10px;" ></input>
		
		
	</div>
</div>

<div id="openModalAnio" class="modalDialog">
	<div>
		<a href="#close" title="Close" class="close">X</a>
		<h2>Ingrese el año:</h2>
		
		<input type="number" min="1900" max="2099" step="1" value="2022" />
		<input  name="buscarAnio" type="submit" value="Buscar" class="btn btn-primary" style="margin-left:10px;" ></input>
		
		
	</div>
</div>

</body>
</html>
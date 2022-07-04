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
 <input name="cerrarSesion" type="submit" value="Cerrar Sesi�n" class="btn btn-primary btn-sm" style="margin-left:10px;"></div>
	<br>
</form>	
	<div class="registro">
		<form>
			<h1>Reportes</h1>
			
			 <div style="display: flex; flex-direction: row; flex-wrap: wrap">
                        <a href="#openModalMedico" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 11rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:50px;">
                                <img src="img/Reportes.png" style="width: 40px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 12px">Total de pacientes atendidos por medico</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="#openModalFechas" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 11rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:80px;">
                                <img src="img/Reportes.png" style="width: 40px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 12px">Total turnos ausente entre fechas</p>
                                </div>
                            </div>
                        </a>
                        
                       
                       <a href="#openModalMes" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 11rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:50px;">
                                <img src="img/Reportes.png" style="width: 40px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 12px">Cantidad turnos atendido por mes y a�o</p>
                                </div>
                            </div>
                        </a>
                        
                        <a href="#openModalAnio" style="text-decoration: none; color: dimgrey;">
                            <div class="card" style="width: 9rem; height: 11rem; border: solid 2px dimgrey; padding: 12px; margin: 10px; margin-left:80px;">
                                <img src="img/Reportes.png" style="width: 40px" class="card-img-top" alt="..." />
                                <div class="card-body">
                                    <p class="card-text" style="font-size: 12px">Porcentaje de asistencia por a�o</p>
                                </div>
                            </div>
                        </a>
                        
                      
                        
                        
			</div>
			</form>
	</div>


	<div id="openModalMedico" class="modalDialog">
		<div>
		<form method="post" action="Reportes">
			<a href="#close" title="Close" class="close">X</a>
			<h2>Seleccione el Medico:</h2>
			<label>Medico:</label> <select class="select" name="medicoReporte">
				<%
					ArrayList<Medico> listaMedico = null;
					if (request.getAttribute("listaMedico") != null) {
						listaMedico = (ArrayList<Medico>) request.getAttribute("listaMedico");
					}

					if (listaMedico != null)
						for (Medico me : listaMedico) {
				%>
				<option value="<%=me.getIdMedico().getIdUsuario()%>">
					<%=me.getNombre()%> <%=me.getApellido()%>
				</option>
				<%
					}
				%>
			</select> <input name="totalPacientes" type="submit" value="Buscar" class="btn btn-primary" style="margin-left: 10px;"></input>
			<br>
			<label><%=session.getAttribute("totalPaciente") %></label>
		</form>
		</div>
	</div>

	<div id="openModalFechas" class="modalDialog">
	<div>
		<a href="#close" title="Close" class="close">X</a>
		<h2>Ingrese las fechas:</h2>
		<label>Primer Fecha:</label><input type="date" name="Fecha1" class="inputForm"></input>
		<label>Segunda Fecha:</label><input type="date" name="Fecha2" class="inputForm" ></input>
		<input  name="buscarFechas" type="submit" value="Buscar" class="btn btn-primary" style="margin-left:10px;" ></input>
		
		
	</div>
</div>


<div id="openModalMes" class="modalDialog">
	<div>
		<a href="#close" title="Close" class="close">X</a>
		<h2>Ingrese el mes y el a�o:</h2>
		<label> Fecha:</label><input type="month" name="Fecha1" class="inputForm"></input>
	
		<input  name="buscarMes" type="submit" value="Buscar" class="btn btn-primary" style="margin-left:10px;" ></input>
		
		
	</div>
</div>

<div id="openModalAnio" class="modalDialog">
	<div>
		<a href="#close" title="Close" class="close">X</a>
		<h2>Ingrese el a�o:</h2>
		
		<input type="number" min="1900" max="2099" step="1" value="2022" />
		<input  name="buscarAnio" type="submit" value="Buscar" class="btn btn-primary" style="margin-left:10px;" ></input>
		
		
	</div>
</div>

</body>
</html>
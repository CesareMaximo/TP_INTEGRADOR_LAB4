<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>Mis turnos asignados</title>
</head>
<body>

	<!--LISTADO DE TURNOS ASIGNADOS CON BUSQUEDA  
EN CADA TURNO PODRA VER LOS DATOS DEL TURNO, AMPLIAR DETALLE DE PACIENTE, AGREGAR OBSERVACIÓN, CAMBIAR ESTADO 
-->

	<% 	
	
	try{
	
		if(session == null){

		}

	
		if (session.getAttribute("tipo").equals("Medico")) {
	%>
	<form method="post" action="logout">
		<div
			style="font-family: Open Sans; margin-top: 6px; float: right; margin-right: 12px; color: #fff; font-size: 12px;">
			<img width="16px" class="imag" src="img/user.png" /> Bienvenido <b><%=session.getAttribute("username")%></b>
			<input name="cerrarSesion" type="submit" value="Cerrar Sesión"
				class="btn btn-primary btn-sm" style="margin-left: 10px;">
		</div>
		<br>
	</form>
	<div class="container-xl">
		<div class="table-responsive">
			<div class="table-wrapper">
				<div style="padding-bottom: 0px;" class="table-title">
					<div class="row justify-content-center">
						<div class="col-sm-8">
							<h1>Mis turnos asignados</h1>
							<table class="filtrosListado">
								<tr>
									<td><label>Estado:</label></td>
									<td><select class="select">
											<option>LIBRE</option>
											<option>OCUPADO</option>
											<option>AUSENTE</option>
											<option>PRESENTE</option>
									</select></td>
									<td><label>Fecha:</label></td>
									<td><input type="date"></td>
									<td><input name="btnFiltrar" type="submit" value="Filtrar"
										class="btn btn-primary btn-sm"></td>
								</tr>

							</table>
						</div>
						<div class="col-sm-4">
							<div class="search-box">
								<i class="material-icons">&#xE8B6;</i> <input type="text"
									class="form-control" placeholder="Search&hellip;">
							</div>
						</div>

						<div></div>
					</div>
				</div>
				<table class="table table-striped table-hover table-bordered">
					<thead>
						<tr>

							<th>Fecha <i class="fa fa-sort"></i>
							</th>
							<th>Horario</th>
							<th>Paciente<i class="fa fa-sort"></i></th>
							<th>Estado</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<tr>

							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td><a href="#openModalDetallePaciente" class="view"
								title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
								<a href="#openModalTurno" class="edit" title="Edit"
								data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="clearfix">
					<div class="hint-text">
						Showing <b>5</b> out of <b>25</b> entries
					</div>
					<ul class="pagination">
						<li class="page-item disabled"><a href="#"><i
								class="fa fa-angle-double-left"></i></a></li>
						<li class="page-item active"><a href="#" class="page-link">1</a></li>
						<li class="page-item"><a href="#" class="page-link">2</a></li>
						<li class="page-item"><a href="#" class="page-link">3</a></li>
						<li class="page-item"><a href="#" class="page-link">4</a></li>
						<li class="page-item"><a href="#" class="page-link">5</a></li>
						<li class="page-item"><a href="#" class="page-link"><i
								class="fa fa-angle-double-right"></i></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div id="openModalDetallePaciente" class="modalDialog">
		<div class="DetallePaciente">
			<a href="#close" title="Close" class="close">X</a>
			<h1>Detalle Paciente</h1>
			<table class="tablaDetalle">
				<tr>
					<td class="Campo"><label>DNI</label></td>
					<td><label name="lblDni"> 12345678</label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Nombres</label></td>
					<td><label name="lblNombre">Juan</label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Apellidos</label></td>
					<td><label name="lblApellido">Perez</label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Fecha Nacimiento</label></td>
					<td><label name="lblFechaNac"> 1997/03/16 </label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Sexo</label></td>
					<td><label name="lblSexo">Masculino</label>
				</tr>
				<tr>
					<td class="Campo"><label>Nacionalidad</label></td>
					<td><label name="lblNacionalidad">Argentina</label>
				</tr>
				<tr>
					<td class="Campo"><label>Provincia</label></td>
					<td><label name="lblProvincia">Buenos Aires</label>
				</tr>
				<tr>
					<td class="Campo"><label>Localidad</label></td>
					<td><label name="lblLocalidad">Campana</label>
				</tr>
				<tr>
					<td class="Campo"><label>Direcci&oacuten</label></td>
					<td><label name="lblDireccion">Av. Mitre 1300</label></td>
				</tr>
				<tr>
					<td class="Campo"><label>E-mail</label></td>
					<td><label name="email ">juanperez@gmail.com</label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Tel&eacutefono</label></td>
					<td><label name="lblTelefono1">11152635262</label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Tel&eacutefono Opcional</label></td>
					<td><label name="lblTelefono2">-</label></td>
				</tr>
			</table>

		</div>
	</div>


	<div id="openModalTurno" class="modalDialog">
		<div class="DetallePaciente">
			<a href="#close" title="Close" class="close">X</a>
			<h1>Detalle Turno</h1>
			<table class="tablaDetalle">
				<tr>
					<td class="Campo"><label>Fecha</label></td>
					<td><label name="lblFecha"> 2022/06/20</label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Hora</label></td>
					<td><label name="lblHora">08:00</label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Paciente</label></td>
					<td><label name="lblPaciente">Juan Perez</label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Especialidad</label></td>
					<td><label name="lblEspecialidad"> Cardiología </label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Estado</label></td>
					<td><select class="select"><Option>OCUPADO</Option>
							<Option>PRESENTE</Option>
							<Option>AUSENTE</Option></select>
				</tr>
				<tr>
					<td class="Campo"><label>Observación</label></td>
					<td><textArea name="txtObservacion" style="resize: none;"
							class="inputForm" cols="26" rows="3" required></textArea>
				</tr>
			</table>
			<input name="actualizarTurno" type="submit" value="Guardar"
				class="btn btn-primary btn-sm btn-block" style="margin-top: 5px;">
		</div>
	</div>
	<%
		} else {

			response.sendRedirect("Error.jsp");
		}
	}
	catch(Exception e){
		response.sendRedirect("Login.jsp");
	}
	finally{
	}
	
	%>
</body>
</html>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import="Entidad.*"%>
    <%@page import="java.util.ArrayList"%>
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
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
<title>Mis turnos asignados</title>
</head>
<body>



	<!--LISTADO DE TURNOS ASIGNADOS CON BUSQUEDA  
EN CADA TURNO PODRA VER LOS DATOS DEL TURNO, AMPLIAR DETALLE DE PACIENTE, AGREGAR OBSERVACIÓN, CAMBIAR ESTADO 
-->


	<form method="post" action="logout">
		<div
			style="font-family: Open Sans; margin-top: 6px; float: right; margin-right: 12px; color: #fff; font-size: 12px;">
			 <a href="MiCuenta.jsp" title="Mi Cuenta" data-toggle="tooltip""><img width="16px"class="imag" src="img/user.png"/></a> Bienvenido <b> <%= session.getAttribute("username") %></b>
			<input name="cerrarSesion" type="submit" value="Cerrar Sesión"
				class="btn btn-primary btn-sm" style="margin-left: 10px;">
		</div>
		<br>
	</form>
	<div class="container-xl">
		<div class="table-responsive">
			<div class="table-wrapper">
				<div style="padding-bottom: 0px;" class="table-title">
					<div class="row justify-content-left">
						<div class="col-sm-8">
							<h1>Mis turnos asignados</h1>
							<table class="filtrosListado">
								<tr>
									<td><label>Estado:</label></td>
									<td><select class="select">
										
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
	
					</div>
				</div>



				<table id="table_turnos" class="display">
					<thead>
						<tr>
							<th>Fecha</th>
							<th>Horario</th>
							<th>Paciente</th>
							<th>Estado</th>
							<th>Acciones</th>
						</tr>
					</thead>
					
					
					<tbody>
					
					<%  
					ArrayList<Turno> listaMisTurnos = null;
					if (request.getAttribute("listaMisTurnos") != null) {
						listaMisTurnos = (ArrayList<Turno>)request.getAttribute("listaMisTurnos");
					}
					for(Turno tu : listaMisTurnos){
					
					%>
						<tr>
						<td><%=tu.getFecha()%></td>
						<td><%=tu.getHora()%></td>
						<td><%=tu.getpPaciente().getNombre()%> <%=tu.getpPaciente().getApellido()%></td>
						<td><%=tu.geteEstado().getDescripcion()%></td>
						<td><a href="ServletIndexMedico?Detalle=<%=tu.getpPaciente().getDni()%>" class="view"
								title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
								<a href="ServletIndexMedico?Modificar=<%=tu.getIdTurno()%>&Pax=<%= tu.getpPaciente().getDni() %>" class="edit" title="Edit"
								data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
							</td>
						</tr>
						<% } %>
					</tbody>
				</table>

				
			</div>
		</div>
	</div>




						<% 		
			
						
						boolean detalle = false;
					
						
						if(request.getAttribute("detallePaciente")!=null){
						
							detalle = (boolean)request.getAttribute("detallePaciente");				
							
						}
						
						Paciente pa = new Paciente();
						
						if(request.getAttribute("Paciente")!=null){
							pa = (Paciente)request.getAttribute("Paciente");
						}

							if(detalle == true){
								%>
								<script type="text/javascript">
										window.onload = function() {
											OpenBootstrapPopup();
										};
										function OpenBootstrapPopup() {
											$("#openModalDetallePaciente").modal('show');
										}
										
									</script>
									
									<div id="openModalDetallePaciente" class="modal fade">
			<div class="modal-dialog modal-det">
				<div class="modal-content">
					<div  class="modal-header justify-content-left">						
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body text-center">
						<h1>Detalle Paciente</h1>	
						<table class="tablaDetalle">
				<tr>
					<td class="Campo"><label>DNI</label></td>
					<td><label name="lblDni"> <%=pa.getDni()%></label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Nombres</label></td>
					<td><label name="lblNombre"> <%=pa.getNombre()%></label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Apellidos</label></td>
					<td><label name="lblApellido"> <%=pa.getApellido()%></label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Fecha Nacimiento</label></td>
					<td><label name="lblFechaNac"> <%=pa.getFechaNacimiento()%> </label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Sexo</label></td>
					<td><label name="lblSexo"> <%=pa.getSexo()%></label>
				</tr>
				<tr>
					<td class="Campo"><label>Nacionalidad</label></td>
					<td><label name="lblNacionalidad"> <%=pa.getnNacionalidad().getDescripcion()%></label>
				</tr>
				<tr>
					<td class="Campo"><label>Provincia</label></td>
					<td><label name="lblProvincia"> <%=pa.getlLocalidad().getpProvincia().getDescripcion()%></label>
				</tr>
				<tr>
					<td class="Campo"><label>Localidad</label></td>
					<td><label name="lblLocalidad"><%=pa.getlLocalidad().getDescripcion()%></label>
				</tr>
				<tr>
					<td class="Campo"><label>Direcci&oacuten</label></td>
					<td><label name="lblDireccion"><%=pa.getDireccion()%></label></td>
				</tr>
				<tr>
					<td class="Campo"><label>E-mail</label></td>
					<td><label name="email "><%=pa.getEmail()%></label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Tel&eacutefono</label></td>
					<td><label name="lblTelefono1"><%=pa.getTelefono1()%></label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Tel&eacutefono Opcional</label></td>
					
					<% if(pa.getTelefono2() == null){%> 
					<td><label name="lblTelefono2"> </label></td>
					<%}else{%>
					<td><label name="lblTelefono2"><%=pa.getTelefono2()%></label></td>
					<%}%>
				</tr>
			</table>
					</div>
					
				</div>
			</div>
		</div>     
									
									
							
									
								 <%}
									%>
									
									<% 		
			
						
						boolean modalEdit = false;
									
						Turno turno = new Turno();
						
						if(request.getAttribute("Turno")!=null){
							turno = (Turno)request.getAttribute("Turno");
						}
					
						
						if(request.getAttribute("ModalEdit")!=null){
						
							modalEdit = (boolean)request.getAttribute("ModalEdit");				
							
						}
						
						Paciente paciente = new Paciente();
						
						if(request.getAttribute("Paciente")!=null){
							paciente = (Paciente)request.getAttribute("Paciente");
						}

							if(modalEdit == true){
								%>
								<script type="text/javascript">
										window.onload = function() {
											OpenBootstrapPopup();
										};
										function OpenBootstrapPopup() {
											$("#openModalDetalleTurno").modal('show');
										}
										
									</script>
									<div id="openModalDetalleTurno" class="modal fade">
									<div class="modal-dialog modal-det">
				<div class="modal-content">
					<div  class="modal-header justify-content-left">						
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body text-center">
					<h1>Detalle Turno</h1>
			<table class="tablaDetalle">
			<form method="post" action="ServletIndexMedico">
				<tr>
					<td></td>
					<td><input type="hidden" name="lblidTurno" value=<%= turno.getIdTurno()%>></td>
				</tr>
				<tr>
					<td class="Campo"><label>Fecha</label></td>
					<td><label name="lblFecha"><%= turno.getFecha()%></label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Hora</label></td>
					<td><label name="lblHora"><%= turno.getHora() %></label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Paciente</label></td>
					<td><label name="lblPaciente"><%= paciente.getNombre()%> <%= paciente.getApellido() %></label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Especialidad</label></td>
					<td><label name="lblEspecialidad"><%= turno.getmMedico().geteEspecialidad().getDescripcion() %> </label></td>
				</tr>
				<tr>
					<td class="Campo"><label>Estado</label></td>
					<td><select class="select" name= "lblEstado"><Option value=2>OCUPADO</Option>
							<Option value=4>PRESENTE</Option>
							<Option value=3>AUSENTE</Option></select>
				</tr>
				<tr>
					<td class="Campo"><label>Observación</label></td>
					<td><textArea  name="txtObservacion" style="resize: none;"
							class="inputForm" cols="26" rows="3"><%= turno.getObservacion() %></textArea>
				</tr>
			</table>
			<input name="actualizarTurno" type="submit" value="Guardar"
				class="btn btn-primary btn-sm btn-block" style="margin-top: 5px;">
			</form>
				</div>
					</div>
				</div>
				</div>
									<%}
									%>
									<% 		
			
						
						boolean exito = false;
						
						if(request.getAttribute("exito")!=null){
						
							exito = (boolean)request.getAttribute("exito");
							
						}
							if(exito == true){
								%>
								<script type="text/javascript">
										window.onload = function() {
											OpenBootstrapPopup();
										};
										function OpenBootstrapPopup() {
											$("#simpleModal").modal('show');
										}
									</script>
									
									<div id="simpleModal" class="modal fade">
			<div class="modal-dialog modal-ok">
				<div class="modal-content">
					<div class="modal-header justify-content-center">
						<div class="icon-box">
							<i style="color: green" class="material-icons">&#xE876;</i>
						</div>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body text-center">
						<h4>Exito!</h4>	
						<p>El Turno ha sido modificado satisfactoriamente.</p>
					</div>
				</div>
			</div>
		</div>     
								 <%} 
									%>


	<script>
		$(document).ready(function() {
			$('#table_turnos').DataTable();
		});
	</script>
	<script>
		var table = $('#table_turnos').DataTable({
			"order": [[0, 'asc'] , [1, 'asc']],
			columnDefs : [ {
				targets : [ 4 ],
				orderable : false
			},

			]
		});
	</script>

</body>
</html>
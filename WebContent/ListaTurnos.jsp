<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import="Entidad.*"%>
    <%@page import="java.util.ArrayList"%>
<%@ page buffer="64kb" %>
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
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
<title>Turnos</title>
</head>



<body onLoad="myOnLoad()">
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
<!-- LISTADO DE TURNOS CON FILTRO POR ESTADO, POR MEDICO, POR PACIENTE, POR FECHA -->

	<div style="float: left; margin-left: 12px; margin-top: 6px;">
		<a href="IndexAdmin.jsp"><img src="img/atras.png" height="20px" /></a>
		<a href="IndexAdmin.jsp"> <img src="img/home.png" height="20px"
			style="margin-left: 10px;" width="20px"></a>
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

	<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
        
          <div style="padding-bottom: 0px;" class="table-title">
                <div class="row justify-content-left">
                    <div class="col-md-6">
                    	<h1>Turnos</h1></div>
                    	<div class="col-md-6 d-flex justify-content-end">
                    	<a style="height:38px;" href="ServletTurno?Agenda=1" name="abrirAgenda" class="btn btn-primary btn-ml">Abrir agenda</a></div>
                    	<form method="post" action="ServletTurno">
                		<table class ="filtrosListado">
                			<tr>
							<td><label>Estado:</label></td>
							<td><select name="slcEstado" class="select">
											<option value=0>TODOS</option>
											<option value=1>LIBRE</option>
											<option value=2>OCUPADO</option>
											<option value=3>AUSENTE</option>
											<option value=4>PRESENTE</option>
									</select></td>
							<td><label>Fecha:</label></td>
								<td><input type="date" name="fechaFiltro"></td>
							
							<td><input name="btnFiltrar" type="submit" value="Filtrar" class="btn btn-primary btn-sm"></td>
						</tr> 
                		</table>
                		</form>
                    </div>  
                    
                </div>
        
       
     
				 <%
					ArrayList<Turno> listaTurnos = (ArrayList<Turno>)session.getAttribute("listaTurnos"); 
					/* if(listaTurnos!= null){
						listaTurnos = (ArrayList<Turno>) request.getAttribute("listaTurnos");
					} */
				 %>

				<table id="table_turnos" class="display">
					   <thead>
                    <tr>
                        <th>#</th>
                        <th>DNI Paciente</th>
                        <th>Medico</th>
                        <th>Especialidad</th>
                        <th>Fecha</th>
                        <th>Horario</th>
                        <th>Estado</th>
                        <th>Acciones </th>
                    </tr>
                </thead>
					 <tbody>
                        <%  if(listaTurnos !=null)
						for(Turno tu : listaTurnos){
					
					%>
	                    <tr>
	                    	<td><%=tu.getIdTurno()%></td>
	                    	<%
								if (tu.getpPaciente().getDni() == null) {
							%><td>Sin Asignar</td>
							<%
								} else {
							%>
							<td><%=tu.getpPaciente().getDni()%></td>
	                    	<%} %>
							<td><%=tu.getmMedico().getNombre()%> <%=tu.getmMedico().getApellido()%></td>
							<td><%=tu.getmMedico().geteEspecialidad().getDescripcion()%></td>
							<td><%=tu.getFecha()%></td>
							<td><%=tu.getHora()%></td>
							<td><%=tu.geteEstado().getDescripcion()%></td>
							<td>
							<% if (tu.geteEstado().getDescripcion().equals("LIBRE")) { %>
	                    	<a href="ServletTurno?AsignarTurno=<%=tu.getIdTurno()%>" class="edit" title="Asignar" data-toggle="tooltip" style="color:green"><i class="material-icons">assignment_ind</i></a>
	                       	<% }
							else { 
								if (tu.geteEstado().getDescripcion().equals("OCUPADO")) {
							%>
	                       	<a href="#myModal" class="delete" title="Liberar" data-toggle="modal" style="color:red" data-turno-id="<%=tu.getIdTurno()%>"><i class="material-icons">event_busy</i></a>

	                       	<% } else {%>
	                       	<a href="ServletTurno?VerDetalleTurno=<%=tu.getIdTurno()%>&Pax=<%= tu.getpPaciente().getDni() %>" class="view" title="Detalle" data-toggle="tooltip" style="color:blue"><i class="material-icons">&#xE417;</i></a>
	                       	<% } %>
	                       	</td>     	
	                 	</tr>
	               	<% } %>
	               	<% } %>
                </tbody>
				</table>

        </div>
    </div>  
</div>
	<%
		boolean exito = false;
			String texto = "";

			if (request.getAttribute("exito") != null) {

		exito = (boolean) request.getAttribute("exito");
		texto = "registrado";

		}
			if (request.getAttribute("exito3") != null ){
				exito = (boolean) request.getAttribute("exito3");
				texto = "liberado";
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

	<div id="simpleModal" class="modal fade">
		<div class="modal-dialog modal-ok">
			<div class="modal-content">
				<div class="modal-header justify-content-center">
					<div class="icon-box">
						<i style="color: green" class="material-icons">&#xE876;</i>
					</div>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body text-center">
					<h4>Exito!</h4>
					<p>
						El turno se ha <%=texto%> satisfactoriamente.
					</p>
				</div>
			</div>
		</div>
	</div>
	<%
		}
	%>
	
	<% 		
						boolean exito2 = false;
						
						if(request.getAttribute("exito2")!=null){
						
							exito2 = (boolean)request.getAttribute("exito2");
							
						}
							if(exito2 == true){
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
						<p>La agenda se ha registrado satisfactoriamente.</p>
					</div>
				</div>
			</div>
		</div>     
								 <%} 
									%>
									<% 		
			
						
						boolean advertencia = false;				
						if(request.getAttribute("advertencia")!=null){
						
							advertencia = (boolean)request.getAttribute("advertencia");
					
							
						}
							
							
							if(advertencia == true){
								%>
								<script type="text/javascript">
										window.onload = function() {
											OpenBootstrapPopup();
										};
										function OpenBootstrapPopup() {
											$("#modalAdvertencia").modal('show');
										}
									</script>
									<div id="modalAdvertencia" class="modal fade">
			<div class="modal-dialog modal-confirm">
				<div class="modal-content">
					<div class="modal-header flex-column">
						<div class="icon-box">
							<i style="color: red" class="material-icons">&#xE5CD;</i>
						</div>	
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body text-center">
					
						<p> Profesional de la Salud ya posee agenda abierta para algunos de los dias seleccionados.
						Se agregaran solo los nuevos.</p>
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
			"order": [[4, 'asc'] , [5, 'asc']],
			columnDefs : [ {
				targets : [ 7 ],
				orderable : false
			},

			]
		});
	</script>
	
	
	<script type="text/javascript">
	                        $(document).ready(function (e) {
	                        	  $('#myModal').on('show.bs.modal', function(e) {
	                        	 
	                        		  var id = $(e.relatedTarget).data('turno-id'); 
	                        		  $(e.currentTarget).find('input[name="turnoId"]').val(id);
										
	                        		  
	                        	  });
	                        	});
							
							</script>	


			<form action="ServletTurno?LiberarTurno=1" method="post">
							<div id="myModal" class="modal fade">
								<div class="modal-dialog modal-confirm">
									<div class="modal-content">
										<div class="modal-header flex-column">
											<div class="icon-box">
												<i class="material-icons">&#xE5CD;</i>
											</div>						
											<h4 class="modal-title w-100">¿Estás seguro?</h4>	
							                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										</div>
										<div class="modal-body">
											<p>¿Desea liberar el turno?</p>
											<input type="hidden" name="turnoId" id="turnoId">
										</div>
										<div class="modal-footer justify-content-center">
											<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
											<button type="submit" class="btn btn-danger">Liberar</button>
										</div>
									</div>
								</div>
							</div>    
						</form>
						
						
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
					<div class="modal-body">
					<h1>Detalle Turno</h1>
			<table class="tablaDetalle">
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
					<td class="Campo"><label>Observación</label></td>
					<td>
					<% if (turno.getObservacion() != null){
						%>
					<textarea rows="4" cols="20" name="txtObservacion" style="resize:none;"><%=turno.getObservacion() %></textarea>
					<%}
					else{%>
					<label  name="txtObservacion"></label>
					<%} %>
					</td>
				</tr>
			</table>
				</div>
					</div>
				</div>
				</div>
									<%}
									%>
	
	
</body>
</html>
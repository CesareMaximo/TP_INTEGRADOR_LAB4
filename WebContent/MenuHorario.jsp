<%@page import="Entidad.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page buffer="64kb" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Horario de Atención</title>

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

</head>

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

<form method="post" action ="logout" >
 <div  style=" font-family:Open Sans; margin-top:6px; float: right; margin-right: 12px; color: #fff; font-size: 12px; ">
 <a href="MiCuenta.jsp" title="Mi Cuenta" data-toggle="tooltip""><img width="16px"class="imag" src="img/user.png"/></a> Bienvenido <b> <%= session.getAttribute("username") %></b>
 <input name="cerrarSesion" type="submit" value="Cerrar Sesión" class="btn btn-primary btn-sm" style="margin-left:10px;"></div>
	<br>
</form>	
<div style="float: left; margin-left: 12px; margin-top:6px;">
<a href="ServletMedico?Param=1"><img src="img/atras.png" height="20px" /></a>
<a href="IndexAdmin.jsp"> <img src="img/home.png" height="20px" style="margin-left:10px;" width="20px" ></a> 
</div>
	<br>
	<div class="container-xl">
		<div class="table-responsive">
        	<div class="table-wrapper">
            	<div style="padding-bottom: 0px;"class="table-title">
            	 <div class="row">
            	 <% 
                	Medico me = (Medico)session.getAttribute("medico");
                	%>
                <div class="col-md-10">
                    <h1>Horarios: Dr/a <%=me.getNombre()%> <%=me.getApellido()%></h1>
                 </div>
                 <div class="col-md-2 d-flex justify-content-end">  
                 <a style="height:38px;" href="Horario?Nuevo=1" name="nuevoHorario" class="btn btn-primary btn-ml">Agregar Horario</a> 
                </div>
                </div>
		            </div>

	                	<%
						ArrayList<DiaXMedico> listaHorario = null;
	                	listaHorario = (ArrayList) session.getAttribute("listaHorario");
						/* if(session.getAttribute("listaHorario")!= null){
							listaHorario = (ArrayList<Horario>) request.getAttribute("listaHorario");
						} */
						%>


					
					<table id="table_id" class="display">
					   	<thead>
	                    <tr>
	                        <th>Dia</th>
	                        <th>Horario Ingreso </th>
	                        <th>Horario Egreso</th>
	                        <th>Edit</th>
	                    </tr>
                	</thead>
					    <tbody>
					     	<%
 					if(listaHorario !=null)
					for(DiaXMedico ho : listaHorario){ 
					%>
						<tr> 
 							<td><%=ho.getDia().getDescripcion()%></td>
							<td><%=ho.getHorarioIngreso()%></td>
							<td><%=ho.getHorarioEgreso()%></td>

                        <td>
                           <a href="Horario?IdD=<%=ho.getDia().getId()%>" class="delete" title="Delete" ><i class="material-icons">&#xE872;</i></a>
                        </td>
        
					<%} %>
						</tr> 
                </tbody>
					</table>

            </div>
        </div>
	</div>
	<script>
			$(document).ready( function () {
			    $('#table_id').DataTable();
			} );
			</script>	
			<script>
			var table = $('#table_id').DataTable( {
			    columnDefs: [
			        { targets: [3], orderable: false},
			     
			    ]
			} );
			</script>	
			
			
		
						<% 		
			
						
						boolean exito = false;
						String texto = "";
						
						if(request.getAttribute("exito")!=null){
						
							exito = (boolean)request.getAttribute("exito");
							 texto = "registrado";
							
						}
						
						if(request.getAttribute("update")!=null){
							
							exito = (boolean)request.getAttribute("update");
							 texto = "modificado";
							
						}
						
						if(request.getAttribute("delete")!=null){
							
							exito = (boolean)request.getAttribute("delete");
							 texto = "eliminado";
							
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
						<p>El horario se ha <%=texto %> satisfactoriamente.</p>
					</div>
				</div>
			</div>
		</div>     
								 <%} 
									%>		
									
									
		<% 		
			
						
						boolean eliminar = false;				
						if(request.getAttribute("eliminar")!=null){
						
							eliminar = (boolean)request.getAttribute("eliminar");
					
							
						}
						int idDia=0;
						if(request.getAttribute("idDia")!=null){
							idDia = (int)request.getAttribute("idDia");
						}
							
							
							if(eliminar == true){
								%>
								<script type="text/javascript">
										window.onload = function() {
											OpenBootstrapPopup();
										};
										function OpenBootstrapPopup() {
											$("#modalEliminar").modal('show');
										}
									</script>
									<form method="post" action="Horario">
									<div id="modalEliminar" class="modal fade">
			<div class="modal-dialog modal-confirm">
				<div class="modal-content">
					<div class="modal-header flex-column">
						<div class="icon-box">
							<i style="color: red" class="material-icons">&#xE5CD;</i>
						</div>
						<h4 class="modal-title w-100">¿Estás seguro?</h4>	
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<input type="hidden" name="idDia" value="<%=idDia%>"/>
					<div class="modal-body text-center">
					
						<p>¿Desea eliminar el horario? Esta operación no se puede deshacer</p>
					</div>
					<div class="modal-footer justify-content-center">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
					<button type="submit" name="btnBorrarHorario" class="btn btn-danger">Borrar</button>
					</div>
				</div>
			</div>
		</div>    
		</form> 
								 <%} 
									%>									
									
									
									
			
	
</body>
</html>
package Servlets;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.DiaXMedico;
import Entidad.Especialidad;
import Entidad.Medico;
import Entidad.Paciente;
import Entidad.Turno;
import Entidad.Usuario;
import Exceptions.PacienteNotFoundException;
import Negocio.DiaXMedicoNegocio;
import Negocio.EspecialidadNegocio;
import Negocio.MedicoNegocio;
import Negocio.PacienteNegocio;
import Negocio.TurnoNegocio;
import NegocioImpl.DiaXMedicoNegocioImpl;
import NegocioImpl.EspecialidadNegocioImpl;
import NegocioImpl.MedicoNegocioImpl;
import NegocioImpl.PacienteNegocioImpl;
import NegocioImpl.TurnoNegocioImpl;

@WebServlet("/ServletTurno")
public class ServletTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletTurno() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MedicoNegocio meNeg = new MedicoNegocioImpl();
		TurnoNegocio tuNeg = new TurnoNegocioImpl();
		Medico me = new Medico();
		EspecialidadNegocio esNeg = new EspecialidadNegocioImpl();
		Especialidad es = new Especialidad();

		ArrayList<Especialidad> listaEspecialidad = (ArrayList<Especialidad>) esNeg.readAll();
		ArrayList<Medico> listaMedico = (ArrayList<Medico>) meNeg.readAll();
		ArrayList<Turno> listaTurno = (ArrayList<Turno>) tuNeg.readAll();

		RequestDispatcher rd;

		if (request.getParameter("Param") != null) {
			ArrayList<Turno> listaTurno2 = (ArrayList<Turno>) tuNeg.readAll();
			request.setAttribute("listaEspecialidad", listaEspecialidad);
			//request.setAttribute("listaTurnos", listaTurno);
			request.setAttribute("listaMedico", listaMedico);
			request.getSession().setAttribute("listaTurnos", listaTurno2);
			request.setAttribute("exito", false);	
			rd = request.getRequestDispatcher("/ListaTurnos.jsp");
			rd.forward(request, response);
		}

		if (request.getParameter("Agenda") != null) {

			request.setAttribute("listaEspecialidad", listaEspecialidad);

			request.setAttribute("listaMedico", listaMedico);

			rd = request.getRequestDispatcher("/AbrirAgenda.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("AsignarTurno") != null) {
			Turno tu = new Turno();
			int id = Integer.parseInt(request.getParameter("AsignarTurno"));
			tu = tuNeg.devuelveTurno(id);
			request.getSession().setAttribute("Turno", tu);
			request.setAttribute("exito", true);	
			rd = request.getRequestDispatcher("/AsignacionTurnos.jsp");
			rd.forward(request, response);
		}
		
		
		if(request.getParameter("VerDetalleTurno") != null && request.getParameter("Pax") != null) {
			
			
			Turno tu = new Turno();
			Paciente pax = new Paciente();
			PacienteNegocio pNeg = new PacienteNegocioImpl();
			int idTurno;
			idTurno = Integer.parseInt(request.getParameter("VerDetalleTurno"));
			String dni;
			dni = request.getParameter("Pax");
			tu = tuNeg.devuelveTurno(idTurno);
			request.setAttribute("Turno", tu);
			pax = pNeg.mostrarPaciente(dni);
			request.setAttribute("Paciente", pax);
			request.setAttribute("ModalEdit", true);
			request.getRequestDispatcher("/ListaTurnos.jsp").forward(request, response);
		
	}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Medico med = new Medico();

		DiaXMedicoNegocio dxmNeg = new DiaXMedicoNegocioImpl();
		TurnoNegocio tneg = new TurnoNegocioImpl();
		Usuario usu = new Usuario();
		ArrayList<Turno> listaAgenda = new ArrayList<Turno>();
		PacienteNegocio pNeg = new PacienteNegocioImpl();
		boolean existe = false;
		Turno turno = new Turno();

		if(request.getParameter("reservar") != null) {
			try {
			pNeg.existePaciente(request.getParameter("txtDni"));
			turno = (Turno)request.getSession().getAttribute("Turno");
			tneg.agendarTurno(request.getParameter("txtDni"), turno);
			request.setAttribute("exito", true);
			ArrayList<Turno> listaTurno2 = (ArrayList<Turno>) tneg.readAll();
			request.getSession().setAttribute("listaTurnos", listaTurno2);
		}
		catch (PacienteNotFoundException e) {
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("AsignacionTurnos.jsp").forward(request, response);
		}
	}
		
		if (request.getParameter("abrirAgenda") != null) {

			usu.setIdUsuario(Integer.parseInt(request.getParameter("slcMedicos")));
			med.setIdMedico(usu);

			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date dateFormateado = new Date();
			try {
				dateFormateado = formato.parse(request.getParameter("FechaApertura"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date inicio = new java.sql.Date(dateFormateado.getTime());

			formato = new SimpleDateFormat("yyyy-MM-dd");
			dateFormateado = new Date();
			try {
				dateFormateado = formato.parse(request.getParameter("FechaCierre"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date fin = new java.sql.Date(dateFormateado.getTime());

			ArrayList<DiaXMedico> diasTrabajo = dxmNeg.readDias(med.getIdMedico().getIdUsuario());
			
			
			try {
				while (inicio.compareTo(fin) == 0 || inicio.compareTo(fin) < 0) {

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(inicio); 

					int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
					if(tneg.existeFechaTurno(med.getIdMedico().getIdUsuario(), inicio) == false){						
						for (DiaXMedico dia : diasTrabajo) {
							
							if (dia.getDia().getId() == dayOfWeek) {
								
								Calendar horaIng = Calendar.getInstance();
								horaIng.setTime(dia.getHorarioIngreso());
								Calendar horaEg = Calendar.getInstance();
								horaEg.setTime(dia.getHorarioEgreso());
								
								while (horaIng.compareTo(horaEg) < 0) {
									Time Cas2 = new Time(horaIng.getTimeInMillis());
									Turno turno2 = new Turno();
									turno2.setmMedico(med);
									turno2.setFecha(inicio);
									turno2.setHora(Cas2);
									
									listaAgenda.add(turno2);
									//System.out.println(listaAgenda.toString());
									horaIng.add(Calendar.HOUR_OF_DAY, 1);															
								}
							}
						}
					}
					calendar.add(Calendar.DATE, 1);
					inicio = new java.sql.Date(calendar.getTimeInMillis());
				}
				
				if(tneg.insert(listaAgenda)) {
					ArrayList<Turno> listaTurno3 = (ArrayList<Turno>) tneg.readAll();
					request.getSession().setAttribute("listaTurnos", listaTurno3);
					request.setAttribute("exito2", true);	
				}else {
					ArrayList<Turno> listaTurno3 = (ArrayList<Turno>) tneg.readAll();
					request.getSession().setAttribute("listaTurnos", listaTurno3);
					request.setAttribute("exito2", false);	
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if(request.getParameter("LiberarTurno") != null) {
			int id = Integer.parseInt(request.getParameter("turnoId"));
			if (tneg.liberarTurno(id)) {
				request.setAttribute("exito3", true);
				ArrayList<Turno> listaTurno = (ArrayList<Turno>) tneg.readAll();
				request.getSession().setAttribute("listaTurnos", listaTurno);
			}
		}
		
		if (request.getParameter("btnFiltrar") != null) {

			int idEstado = Integer.parseInt(request.getParameter("slcEstado"));

			if (request.getParameter("fechaFiltro") != "") {

				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Date dateFormateado = new Date();
				try {
					dateFormateado = formato.parse(request.getParameter("fechaFiltro"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				java.sql.Date fechaFiltro = new java.sql.Date(dateFormateado.getTime());

				if (idEstado != 0) {

					
					ArrayList<Turno> listaTurnoFiltros =tneg.filtroFechaEstado(idEstado, fechaFiltro);
					request.getSession().setAttribute("listaTurnos", listaTurnoFiltros);
					// CONSULTA BUSQUEDA X DOS FILTROS
				} else {

					
					
					ArrayList<Turno> listaTurnoFecha =	tneg.filtroFecha(fechaFiltro);
					request.getSession().setAttribute("listaTurnos", listaTurnoFecha);
					// CONSULTA BUSQUEDA SOLO POR FECHA

				}

			} else {

				if (idEstado != 0) {

				ArrayList<Turno> listaTurnoEstado =	tneg.filtroEstado(idEstado);
				request.getSession().setAttribute("listaTurnos", listaTurnoEstado);
				} else {

					ArrayList<Turno> listaTurno = (ArrayList<Turno>) tneg.readAll();
					request.getSession().setAttribute("listaTurnos", listaTurno);	
					// LISTA SIN FILTRO
				}

			}

		}
		
		request.getRequestDispatcher("/ListaTurnos.jsp").forward(request, response);	
		
		
		
			
	}
	
	
}

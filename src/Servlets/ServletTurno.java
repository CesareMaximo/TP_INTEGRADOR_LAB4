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
import Entidad.Turno;
import Entidad.Usuario;
import Negocio.DiaXMedicoNegocio;
import Negocio.EspecialidadNegocio;
import Negocio.MedicoNegocio;
import Negocio.TurnoNegocio;
import NegocioImpl.DiaXMedicoNegocioImpl;
import NegocioImpl.EspecialidadNegocioImpl;
import NegocioImpl.MedicoNegocioImpl;
import NegocioImpl.TurnoNegocioImpl;

/**
 * Servlet implementation class ServletTurno
 */
@WebServlet("/ServletTurno")
public class ServletTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletTurno() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MedicoNegocio meNeg = new MedicoNegocioImpl();
		Medico me = new Medico();
		EspecialidadNegocio esNeg = new EspecialidadNegocioImpl();
		Especialidad es = new Especialidad();

		ArrayList<Especialidad> listaEspecialidad = (ArrayList<Especialidad>) esNeg.readAll();

		ArrayList<Medico> listaMedico = (ArrayList<Medico>) meNeg.readAll();

		RequestDispatcher rd;

		if (request.getParameter("Param") != null) {

			request.setAttribute("listaEspecialidad", listaEspecialidad);

			request.setAttribute("listaMedico", listaMedico);

			rd = request.getRequestDispatcher("/AsignacionTurnos.jsp");
			rd.forward(request, response);
		}

		if (request.getParameter("Agenda") != null) {

			request.setAttribute("listaEspecialidad", listaEspecialidad);

			request.setAttribute("listaMedico", listaMedico);

			rd = request.getRequestDispatcher("/AbrirAgenda.jsp");
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Medico med = new Medico();

		DiaXMedicoNegocio dxmNeg = new DiaXMedicoNegocioImpl();
		TurnoNegocio tneg = new TurnoNegocioImpl();
		Usuario usu = new Usuario();
		ArrayList<Turno> listaAgenda = new ArrayList<Turno>();

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

					for (DiaXMedico dia : diasTrabajo) {

						if (dia.getDia().getId() == dayOfWeek) {

							Calendar horaIng = Calendar.getInstance();
							horaIng.setTime(dia.getHorarioIngreso());
							Calendar horaEg = Calendar.getInstance();
							horaEg.setTime(dia.getHorarioEgreso());

							while (horaIng.compareTo(horaEg) < 0) {
								Time Cas2 = new Time(horaIng.getTimeInMillis());
								Turno turno = new Turno();
								turno.setmMedico(med);
								turno.setFecha(inicio);
								turno.setHora(Cas2);

								listaAgenda.add(turno);

								horaIng.add(Calendar.HOUR_OF_DAY, 1);
									///CARGAR ESTA LISTA EN LA BASE DE DATOS. TABLA TURNO
							}
						}

					}
					calendar.add(Calendar.DATE, 1);
					inicio = new java.sql.Date(calendar.getTimeInMillis());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}

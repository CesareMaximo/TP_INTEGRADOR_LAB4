package Servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Medico;
import Negocio.MedicoNegocio;
import Negocio.TurnoNegocio;
import NegocioImpl.MedicoNegocioImpl;
import NegocioImpl.TurnoNegocioImpl;


@WebServlet("/Reportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletReportes() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MedicoNegocio meNeg = new MedicoNegocioImpl();
		Medico me = new Medico();
		if(request.getParameter("Param")!=null) {
			ArrayList<Medico> listaMedico = (ArrayList<Medico>) meNeg.readAll();
			request.setAttribute("exito", true);
			request.setAttribute("listaMedico", listaMedico);
			request.getRequestDispatcher("/MenuReportes.jsp").forward(request, response);
		}
		
		if(request.getParameter("Modal2") != null){
			request.setAttribute("exito2", true);
			request.getRequestDispatcher("/MenuReportes.jsp").forward(request, response);
		}
		
		if(request.getParameter("Modal3") != null){
			request.setAttribute("exito3", true);
			request.getRequestDispatcher("/MenuReportes.jsp").forward(request, response);
		}
		
		if(request.getParameter("Modal4") != null){
			request.setAttribute("exito4", true);
			request.getRequestDispatcher("/MenuReportes.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MedicoNegocio meNeg = new MedicoNegocioImpl();
		Medico me = new Medico();
		TurnoNegocio tuNeg = new TurnoNegocioImpl();
		ArrayList<Medico> listaMedico = (ArrayList<Medico>) meNeg.readAll();
		
		if(request.getParameter("totalPacientes")!=null) {
					
			int idMedico = Integer.parseInt(request.getParameter("medicoReporte").toString());
			if( idMedico == 0) {
				
				
				request.setAttribute("medico", me);
				request.setAttribute("listaMedico", listaMedico);
				request.setAttribute("advertencia", "Debe seleccionar todos los campos.");
				request.setAttribute("exito", true);
				request.getRequestDispatcher("MenuReportes.jsp").forward(request, response);
				
			}else {
				
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Date dateFormateado = new Date();
				Date fecha1 = new Date();
				Date fecha2 = new Date();
				try {
					dateFormateado = formato.parse(request.getParameter("Fecha1"));
					fecha1 = formato.parse(request.getParameter("Fecha1"));
				} catch (ParseException e) {
					e.printStackTrace();
				}  
				java.sql.Date date1 = new java.sql.Date(dateFormateado.getTime());
				formato = new SimpleDateFormat("yyyy-MM-dd");
				dateFormateado = new Date();
				try {
					dateFormateado = formato.parse(request.getParameter("Fecha2"));
					fecha2 = formato.parse(request.getParameter("Fecha2"));
				} catch (ParseException e) {
					e.printStackTrace();
				}  
				
				if(fecha2.before(fecha1)) {
					
					request.setAttribute("advertencia", "La fecha de cierre tiene que ser mayor a la de apertura");
					
					
					request.setAttribute("listaMedico", listaMedico);
			
					request.setAttribute("exito", true);
					request.getRequestDispatcher("MenuReportes.jsp").forward(request, response);
				}
				else {
				
				java.sql.Date date2 = new java.sql.Date(dateFormateado.getTime());
				int total = meNeg.totalPacientesXMedico(idMedico, date1, date2);
				
				me = meNeg.mostrarMedico(idMedico);
				request.setAttribute("medico", me);
				request.setAttribute("listaMedico", listaMedico);
				request.setAttribute("totalPaciente", total);
				request.setAttribute("fecha1", date1);
				request.setAttribute("fecha2", date2);
				request.setAttribute("exito", true);
				request.getRequestDispatcher("MenuReportes.jsp").forward(request, response);
			}
			}
		}
		
		if(request.getParameter("btnTotalAusentes")!=null) {
			
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date dateFormateado = new Date();
			Date fecha1 = new Date();
			Date fecha2 = new Date();
			try {
				dateFormateado = formato.parse(request.getParameter("Fecha1"));
				fecha1 = formato.parse(request.getParameter("Fecha1"));
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			java.sql.Date date1 = new java.sql.Date(dateFormateado.getTime());
			formato = new SimpleDateFormat("yyyy-MM-dd");
			dateFormateado = new Date();
			try {
				dateFormateado = formato.parse(request.getParameter("Fecha2"));
				fecha2 = formato.parse(request.getParameter("Fecha2"));
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			java.sql.Date date2 = new java.sql.Date(dateFormateado.getTime());
			
			if(fecha2.before(fecha1)) {
				
				request.setAttribute("advertencia2", "La fecha de cierre tiene que ser mayor a la de apertura");
				
				
				request.setAttribute("exito2", true);
				
				request.getRequestDispatcher("MenuReportes.jsp").forward(request, response);
			}
			else {
			
			int totalAusentes = tuNeg.totalAusentes(date1, date2);
			
			request.setAttribute("totalAusentes", totalAusentes);
			request.setAttribute("fecha1", date1);
			request.setAttribute("fecha2", date2);
			request.setAttribute("exito2", true);
			request.getRequestDispatcher("MenuReportes.jsp").forward(request, response);
		}
		}
		
		
		if(request.getParameter("btnTotalAtendidos")!=null) {
			
			int mes = Integer.parseInt(request.getParameter("slcMes"));
			int anio = Integer.parseInt(request.getParameter("slcAnio"));

			int totalAtendidos = tuNeg.totalAtendidosPorMes(mes, anio);

			
			request.setAttribute("totalAtendidos", totalAtendidos);
			request.setAttribute("mes", mes);
			request.setAttribute("anio", anio);
			request.setAttribute("exito3", true);
			request.getRequestDispatcher("MenuReportes.jsp").forward(request, response);
		}
		
		if(request.getParameter("btnBuscarAnio")!=null) {
			
			int anio = Integer.parseInt(request.getParameter("anio"));
			

			int total = tuNeg.total(anio);
			int totalPacientes = tuNeg.totalPresentes(anio);
			
			float porcentaje;
			
			try {
				porcentaje = (float)totalPacientes*100/total;
				if(Float.isNaN(porcentaje)) {
					porcentaje = 0;
				}
			} catch (ArithmeticException e) {
				porcentaje = 0;
			}			
		
			request.setAttribute("porcentaje", porcentaje);
			request.setAttribute("anio", anio);
			request.setAttribute("exito4", true);
			request.getRequestDispatcher("MenuReportes.jsp").forward(request, response);
		}
		
	}

}

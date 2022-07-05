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
import NegocioImpl.MedicoNegocioImpl;


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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MedicoNegocio meNeg = new MedicoNegocioImpl();
		Medico me = new Medico();
		if(request.getParameter("totalPacientes")!=null) {
			int idMedico = Integer.parseInt(request.getParameter("medicoReporte").toString());
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date dateFormateado = new Date();
			try {
				dateFormateado = formato.parse(request.getParameter("Fecha1"));
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			java.sql.Date date1 = new java.sql.Date(dateFormateado.getTime());
			formato = new SimpleDateFormat("yyyy-MM-dd");
			dateFormateado = new Date();
			try {
				dateFormateado = formato.parse(request.getParameter("Fecha2"));
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			java.sql.Date date2 = new java.sql.Date(dateFormateado.getTime());
			int total = meNeg.totalPacientesXMedico(idMedico, date1, date2);
			ArrayList<Medico> listaMedico = (ArrayList<Medico>) meNeg.readAll();
			me = meNeg.mostrarMedico(idMedico);
			request.setAttribute("medico", me);
			request.setAttribute("listaMedico", listaMedico);
			request.setAttribute("totalPaciente", total);
			request.setAttribute("exito", true);
			request.getRequestDispatcher("MenuReportes.jsp").forward(request, response);
		}
		
	}

}

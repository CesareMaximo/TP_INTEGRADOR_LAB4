package Servlets;

import java.io.IOException;
import java.util.ArrayList;

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
			request.setAttribute("listaMedico", listaMedico);
			request.getRequestDispatcher("/MenuReportes.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MedicoNegocio meNeg = new MedicoNegocioImpl();
		Medico me = new Medico();
		if(request.getParameter("totalPacientes")!=null) {
			int idMedico = Integer.parseInt(request.getParameter("medicoReporte").toString());	
			int total = meNeg.totalPacientesXMedico(idMedico);
			request.setAttribute("totalPaciente", total);
			request.getRequestDispatcher("MenuReportes.jsp").forward(request, response);
		}
		
	}

}

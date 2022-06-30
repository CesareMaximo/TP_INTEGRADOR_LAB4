package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Horario;
import Entidad.Medico;
import Negocio.HorarioNegocio;
import Negocio.MedicoNegocio;
import NegocioImpl.HorarioNegocioImpl;
import NegocioImpl.MedicoNegocioImpl;


@WebServlet("/Horario")
public class ServletHorario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletHorario() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Medico me = new Medico();
		int idMedico;
		MedicoNegocio meNeg = new MedicoNegocioImpl();
		HorarioNegocio hoNeg = new HorarioNegocioImpl();
		RequestDispatcher rd;
		if(request.getParameter("param") != null) {
			idMedico = Integer.parseInt((request.getParameter("param").toString()));
			me = meNeg.mostrarMedico(idMedico);
			ArrayList<Horario> listaHorario = (ArrayList<Horario>) hoNeg.readall(idMedico);
			request.getSession().setAttribute("medico", me);
			request.getSession().setAttribute("listaHorario", listaHorario);
			rd = request.getRequestDispatcher("/MenuHorario.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("Nuevo") != null) {
			request.getRequestDispatcher("/AgregarHorario.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

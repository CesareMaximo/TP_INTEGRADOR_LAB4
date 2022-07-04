package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Paciente;
import Entidad.Turno;
import Negocio.PacienteNegocio;
import Negocio.TurnoNegocio;
import NegocioImpl.PacienteNegocioImpl;
import NegocioImpl.TurnoNegocioImpl;

/**
 * Servlet implementation class ServletIndexMedico
 */
@WebServlet("/ServletIndexMedico")
public class ServletIndexMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletIndexMedico() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		TurnoNegocio tNeg = new TurnoNegocioImpl();
		PacienteNegocio pNeg = new PacienteNegocioImpl();
		Paciente pa = new Paciente();
		int idMedico = 0;
		if(request.getSession().getAttribute("idUsuario") != null) {			
			idMedico = (int)request.getSession().getAttribute("idUsuario");
		}
		ArrayList<Turno> listaMisTurnos = (ArrayList<Turno>) tNeg.readPorMedico(idMedico);
		request.setAttribute("listaMisTurnos", listaMisTurnos);
		RequestDispatcher rd;
		
		
		if(request.getParameter("Index") != null) {
		
			request.setAttribute("listaMisTurnos", listaMisTurnos);
			rd = request.getRequestDispatcher("/IndexMedico.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("Detalle") != null) {
			
			
			String dni = request.getParameter("Detalle");
			pa = pNeg.mostrarPaciente(dni);
			
			request.setAttribute("Paciente", pa);
			request.setAttribute("detallePaciente", true);
			rd = request.getRequestDispatcher("/IndexMedico.jsp");
			rd.forward(request, response);
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

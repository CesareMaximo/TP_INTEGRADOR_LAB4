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
import Negocio.PacienteNegocio;
import NegocioImpl.PacienteNegocioImpl;

/**
 * Servlet implementation class ServletPaciente
 */
@WebServlet("/ServletPaciente")
public class ServletPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPaciente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PacienteNegocio paNeg = new PacienteNegocioImpl();
		Paciente pa = new Paciente();
		
		ArrayList<Paciente> listaPaciente = (ArrayList<Paciente>) paNeg.readAll();
		RequestDispatcher rd;
		if(request.getParameter("Param")!= null) {
			request.setAttribute("listaPaciente", listaPaciente);
			
			rd = request.getRequestDispatcher("/MenuPaciente.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
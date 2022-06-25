package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Medico;
import Negocio.MedicoNegocio;
import NegocioImpl.MedicoNegocioImpl;

/**
 * Servlet implementation class ServletMedico
 */
@WebServlet("/ServletMedico")
public class ServletMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMedico() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MedicoNegocio meNeg = new MedicoNegocioImpl();
		Medico me = new Medico();
		
		ArrayList<Medico> listaMedico = (ArrayList<Medico>) meNeg.readAll();
		RequestDispatcher rd;
		
		if (request.getParameter("Param") != null) {
			request.setAttribute("listaMedico", listaMedico);
			rd = request.getRequestDispatcher("/MenuMedico.jsp");
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

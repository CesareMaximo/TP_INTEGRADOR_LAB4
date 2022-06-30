package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Medico;


@WebServlet("/Horario")
public class ServletHorario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletHorario() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Medico me = new Medico();
		int idMedico;
		if(request.getParameter("param") != null) {
			idMedico = Integer.parseInt((request.getParameter("param").toString()));
			//CREAR FUNCION BUSCAR MEDICO Y CARGARLO EN UN PARAMETRO Y MOSTRAR EN LA PANTALLA: NOMBRE Y APELLIDO
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

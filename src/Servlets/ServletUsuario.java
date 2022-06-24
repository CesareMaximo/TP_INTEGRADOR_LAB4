package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DaoImpl.UsuarioImpl;
import Entidad.Usuario;


@WebServlet("/usuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("username");
		String clave = request.getParameter("pass");

		UsuarioImpl imp = new UsuarioImpl();
		Usuario usuario = imp.iniciar(nombre, clave);

		if (usuario == null) {
			request.setAttribute("mensaje", "Error nombre de usuario y/o clave");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		} else {
			//GUARDO LA SESION
			HttpSession sesion = request.getSession();
			sesion.setAttribute("username", usuario.getNombreUsuario());
			if(usuario.getTipo().equals("Admin")) {
				response.sendRedirect("IndexAdmin.jsp");				
			}
			else {
				response.sendRedirect("IndexMedico.jsp");
			}
		}
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}

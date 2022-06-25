package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.glass.ui.Window;
import com.sun.java.swing.plaf.windows.resources.windows;

import DaoImpl.UsuarioDAOImpl;
import Entidad.Usuario;
import Negocio.UsuarioNegocio;
import NegocioImpl.UsuarioNegocioImpl;


@WebServlet("/usuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }


	


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UsuarioNegocio usNeg = new UsuarioNegocioImpl();
		Usuario user = new Usuario();
	
		if (request.getParameter("btnNuevoUser") != null) {
			
			String pass1 = request.getParameter("txtPass");
			String pass2 = request.getParameter("txtPass2");
			request.setAttribute("exito", false);
			if( !(pass1.equals(pass2))) {
				request.setAttribute("mensaje", "Las contraseñas no coinciden");
				request.getRequestDispatcher("AgregarAdministrativo.jsp").forward(request, response);

			}
			else {
				
				String us = request.getParameter("txtUser");
				
				if(usNeg.existe(us)) {
					request.setAttribute("mensaje", "Nombre de usuario no disponible");
					request.getRequestDispatcher("AgregarAdministrativo.jsp").forward(request, response);
				}
				else {
					
					user.setNombreUsuario(request.getParameter("txtUser"));
					user.setClave(request.getParameter("txtPass"));
					user.setTipo("Admin");
					user.setEstado(true);
					if(usNeg.insert(user) == true) {
						request.setAttribute("exito", true);
						request.setAttribute("txtUser", "");
						request.setAttribute("txtPass", "");
						request.setAttribute("mensaje", "");
						
					}

					request.getRequestDispatcher("AgregarAdministrativo.jsp").forward(request, response);

					///VER CONFIRMACIÓN 
				}
			}
			
			
			
		}
		
		
		
	}
	

}

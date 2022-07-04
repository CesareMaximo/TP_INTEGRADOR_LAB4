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
		request.setAttribute("exito", false);
		request.getRequestDispatcher("AgregarAdministrativo.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UsuarioNegocio usNeg = new UsuarioNegocioImpl();
		Usuario user = new Usuario();
		boolean exito = false;
		request.setAttribute("exito", exito);
		
		if (request.getParameter("btnModificarUser") != null) {
			int idUser = Integer.parseInt(request.getSession().getAttribute("idUsuario").toString());
			String userName = request.getSession().getAttribute("username").toString();
			String newUsername = request.getParameter("txtUserNuevo");
			String pass1 = request.getParameter("txtPassAnterior");
			String pass2 = request.getParameter("txtPassNueva");
			String pass3 = request.getParameter("txtPassNueva2");
			exito = false;
			String passSession = (request.getSession().getAttribute("password")).toString();
			
				if (pass2 == "" && pass3 == "" && newUsername == "") {
					request.setAttribute("mensaje", "No está realizando ningún cambio");
					request.setAttribute("exito", exito);
					request.getRequestDispatcher("MiCuenta.jsp").forward(request, response);
				}
				else {
				if(!(pass1.equals(passSession))) {
					request.setAttribute("mensaje", "La contraseña anterior no es correcta");
					request.setAttribute("exito", exito);
					request.getRequestDispatcher("MiCuenta.jsp").forward(request, response);
				}
				else {
			
				if( !(pass2.equals(pass3))) {
					request.setAttribute("mensaje", "Las contraseñas no coinciden");
					request.setAttribute("exito", exito);
					request.getRequestDispatcher("MiCuenta.jsp").forward(request, response);

				}
				else {
					String us = request.getParameter("txtUserNuevo");
					if(usNeg.existe(us)) {
						request.setAttribute("mensaje", "Nombre de usuario no disponible");
						request.setAttribute("exito", exito);
						request.getRequestDispatcher("MiCuenta.jsp").forward(request, response);
					}
					
					else {
						if(newUsername == "") {
							user.setNombreUsuario(userName);
						}
						else {
							user.setNombreUsuario(newUsername);							
						}
						if (pass2 == "") {
							user.setClave(passSession);
						}
						else {
							user.setClave(request.getParameter("txtPassNueva"));							
						}
						user.setIdUsuario(idUser);
						exito = true;
						if(usNeg.update(user) == true) {
							request.setAttribute("exito", true);
							request.setAttribute("txtUseNuevor", "");
							request.setAttribute("txtPassAnterior", "");
							request.setAttribute("txtPassNueva", "");
							request.setAttribute("txtPassNueva2", "");
							request.setAttribute("mensaje", "");
							request.getSession().setAttribute("username", user.getNombreUsuario());
							request.getSession().setAttribute("pass", user.getClave());
							request.getRequestDispatcher("MiCuenta.jsp").forward(request, response);
						}
					}
					
				}
				
			}
		}	
		}
		
		if (request.getParameter("btnNuevoUser") != null) {
			
			String pass1 = request.getParameter("txtPass");
			String pass2 = request.getParameter("txtPass2");
			exito = false;
			if( !(pass1.equals(pass2))) {
				request.setAttribute("mensaje", "Las contraseñas no coinciden");
				request.setAttribute("exito", exito);
				request.getRequestDispatcher("AgregarAdministrativo.jsp").forward(request, response);

			}
			else {
				
				String us = request.getParameter("txtUser");
				
				if(usNeg.existe(us)) {
					request.setAttribute("mensaje", "Nombre de usuario no disponible");
					request.setAttribute("exito", exito);
					request.getRequestDispatcher("AgregarAdministrativo.jsp").forward(request, response);
				}
				else {
					
					user.setNombreUsuario(request.getParameter("txtUser"));
					user.setClave(request.getParameter("txtPass"));
					user.setTipo("Admin");
					user.setEstado(true);
					exito = true;
					if(usNeg.insert(user) == true) {
						request.setAttribute("exito", true);
						request.setAttribute("txtUser", "");
						request.setAttribute("txtPass", "");
						request.setAttribute("mensaje", "");
						
						request.getRequestDispatcher("AgregarAdministrativo.jsp").forward(request, response);
					}
				}
				
			}
			
			
			
		}
		
		
		
	}
	

}

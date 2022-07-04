package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entidad.Usuario;
import Negocio.UsuarioNegocio;
import NegocioImpl.UsuarioNegocioImpl;


@WebServlet("/login")
public class ServletLogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("username");
		String clave = request.getParameter("pass");

		UsuarioNegocio usNeg = new UsuarioNegocioImpl();
		Usuario usuario = usNeg.iniciar(nombre, clave); // TRAE USUARIO Y CONTRASEÑA 
		
		if (usuario == null) {
			request.setAttribute("mensaje", "Error nombre de usuario y/o clave");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		} else {
			//GUARDO LA SESION
			HttpSession sesion = request.getSession();
			sesion.setAttribute("username", usuario.getNombreUsuario());
			sesion.setAttribute("tipo", usuario.getTipo());
			sesion.setAttribute("idUsuario", usuario.getIdUsuario());
			sesion.setAttribute("password", usuario.getClave());
			if(usuario.getTipo().equals("Admin")) {
				response.sendRedirect("IndexAdmin.jsp");				
			}
			else {
				response.sendRedirect("IndexMedico.jsp");
			}
		}
		
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recogida del parametro que determina la operacion a realizar
		String op=request.getParameter("op");
		//direcci�n a la que ser� transferida la petici�n
		String url="inicio.html";
		switch(op){			
			case "doEliminar":
				request.getRequestDispatcher("EliminarAction").include(request, response);
				url="contactos.jsp";
				break;
			case "doListado":
				request.getRequestDispatcher("ListadoAction").include(request, response);
				url="contactos.jsp";
				break;
			case "doAlta":
				request.getRequestDispatcher("alta.jsp").include(request, response);
				request.getRequestDispatcher("AltaAction").include(request, response);
				url="inicio.html";
				break;
			case "doBuscar":
				//trasnferimos el control y luego abandonamos el controller
				request.getRequestDispatcher("BuscarAction").forward(request, response);
				return;
			case "toBuscador":
				url="buscador.html";
				break;
			case "toDatos":
				url="datos.html";
				break;
			case "toInicio":
				url="inicio.html";
		}
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}

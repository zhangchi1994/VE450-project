package ve450.ruix;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ve450.ruix.sql_id_connection.loginReturn;

/**
 * Servlet implementation class ActionServlet
 */
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActionServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = null;
		String password = null;
		String answer = "test";
		username = request.getParameter("id").toString();
		password = request.getParameter("pd").toString();
		sql_id_connection sql_login = new sql_id_connection();
		loginReturn tmp = sql_login.logIN(username, password);
		if (tmp.find_or_not)
			answer = tmp.permission;
		else answer = "Username/Password error";
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(answer);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = null;
		String password = null;
		String type = null;
		String answer = "test";		

		username = request.getParameter("id").toString();
		password = request.getParameter("pd").toString();
		type = request.getParameter("ty").toString();
		sql_id_connection sql_signup = new sql_id_connection();
		if (sql_signup.SignUp(username, password,type))
			answer = "Welcome";
		else answer = "ERROR";
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(answer);
	}
}
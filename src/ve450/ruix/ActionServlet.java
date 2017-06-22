package ve450.ruix;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		sql_connection sql_login = new sql_connection();
		if (sql_login.logIN(username, password))
			answer = "Welcome";
		else answer = "NO WAY";
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(answer);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
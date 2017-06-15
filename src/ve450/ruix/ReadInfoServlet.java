package ve450.ruix;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActionServlet
 */

public class ReadInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReadInfoServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = null;
		//String answer = "test";
		String[] getResult = null;

		id = request.getParameter("id").toString();
		sql_connection sql_login = new sql_connection();
		getResult = sql_login.Read(id);
		//System.out.println(getResult[1]);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		request.setAttribute("value1",getResult[1]);
		request.getRequestDispatcher("/fatherPage.jsp").forward(request, response);
		System.out.println("dispatcher ready");
		//response.getWriter().write(getResult[1]);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
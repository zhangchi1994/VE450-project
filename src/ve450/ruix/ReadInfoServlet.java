package ve450.ruix;

import java.io.IOException;
import java.io.PrintWriter;

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
		String name = null;
		// String answer = "test";
		
		sql_connection sql_login = new sql_connection();
		
		if (request.getParameter("sel") != null) {
			if (request.getParameter("sel").equals("take_out") || request.getParameter("sel").equals("store_used")) {
				id = request.getParameter("id");
				String tmp = sql_login.Read(id);
				System.out.println(tmp);
		        response.setContentType("text/plain;charset=utf-8");
		        request.setCharacterEncoding("utf-8");
		        PrintWriter out = response.getWriter();
		        out.println(tmp);
			}
		} else if (request.getParameter("name") != null) {
			name = request.getParameter("name").toString();
			String tmp = sql_login.ViewStock(name);
			System.out.println(tmp);
	        response.setContentType("text/plain;charset=utf-8");
	        request.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        out.println(tmp);
		}

		/*id = request.getParameter("id").toString();
		getResult = sql_login.Read(id);
		// System.out.println(getResult[1]);

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(getResult);*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String selection = null;
		String id = null;
		String name = null;

		selection = request.getParameter("wmSelection").toString();
		sql_connection sql_login = new sql_connection();
		if (selection.equals("take_out")) {
			id = request.getParameter("id").toString();
			if (sql_login.TakeOutFromWarehouse(id)) {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Take out OK");
			} else {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Take out FAILED");
			}
		} else if (selection.equals("store_used")) {
			id = request.getParameter("id").toString();
			if (sql_login.PutUsedThingBackToWarehouse(id)) {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Store used OK");
			} else {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Store used FAILED");
			}
		} 
	}
}
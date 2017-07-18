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
		String tmp = null;
		
		sql_connection sql_login = new sql_connection();
		if (request.getParameter("sele") != null) {
			if (request.getParameter("sele").equals("viewME")) {
				id = request.getParameter("name");
				tmp = sql_login.ViewStatus(id);
			} else if (request.getParameter("sele").equals("allreport")) {
				System.out.println("flagYES");
				tmp = sql_login.ViewProblemList();
			}
		} else if (request.getParameter("sel") != null) {
			if (request.getParameter("sel").equals("take_out") || request.getParameter("sel").equals("store_used")) {
				id = request.getParameter("id");
				tmp = sql_login.Read(id);
			}
		} else if (request.getParameter("name") != null) {
			System.out.println("flagNO");
			name = request.getParameter("name").toString();
			tmp = sql_login.ViewStock(name);
		}
		System.out.println(tmp);
        response.setContentType("text/plain;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println(tmp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
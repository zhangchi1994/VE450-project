package ve450.ruix;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActionServlet
 */

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String product_name = null;
		String manufacturer = null;
		String time = null;
		String size = null;

		product_name = request.getParameter("pd_name").toString();
		manufacturer = request.getParameter("manu").toString();
		time = request.getParameter("time").toString();
		size = request.getParameter("size").toString();
		sql_connection sql_insert = new sql_connection();
		String return_id = sql_insert.Insert(product_name, manufacturer, time, size);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(return_id);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}

package ve450.ruix;

import java.util.*;  
import java.io.*; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActionServlet
 */

public class GotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GotoServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String value1 = request.getAttribute("value1").toString();
		System.out.println("I got it");
		System.out.println(value1);
		
		response.setContentType("text/plain");
		response.sendRedirect("father_page.html");
		System.out.println("jump ready");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String QRid = null;
		String ss = null;
		QRid = request.getParameter("id").toString();
		ss = request.getParameter("startORstop").toString();
		
		sql_connection conn = new sql_connection();
		//if(ss == "start")
			//conn.Start
	}
}
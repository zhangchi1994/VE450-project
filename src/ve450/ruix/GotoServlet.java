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
		String id = null; 
    	String report = null;
    	String user = null;
    	String img = null;
    	String sel = null;
    	String tmp = "report OK";
    	
    	sel =  request.getParameter("selection").toString();
    	id =  request.getParameter("id").toString();
    	sql_connection conn = new sql_connection();
    	
    	if (sel.equals("whereToview")) {
    		tmp = conn.FindLevel(id);
    	} else if (sel.equals("reportEO")) {
        	report =  request.getParameter("report").toString();
        	user =  request.getParameter("user").toString();
        	img =  request.getParameter("img").toString();
        	conn.Report(id, report, user, img);
    	} else if (sel.equals("reportME")) {
        	report =  request.getParameter("report").toString();
        	user =  request.getParameter("user").toString();
    		conn.ReportForME(id, report, user);
    	}
    	
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(tmp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String QRid = null;
		String ss = null;
		QRid = request.getParameter("id").toString();
		ss = request.getParameter("startORstop").toString();
		sql_connection conn = new sql_connection();
		//System.out.println("ss = "+ss);
		if(ss.equals("start")){
			conn.StartUse(QRid);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("Start OK");
			System.out.println("start not end");
		} else if(ss.equals("finish")){
			conn.EndUse(QRid);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("Stop OK");
		}
	}
}
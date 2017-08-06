package ve450.ruix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;

import sun.misc.BASE64Decoder;

public class SaveIMGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveIMGServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String B64 = request.getParameter("data").toString();
		String name = request.getParameter("name").toString();
		String sel = request.getParameter("selection").toString();
		String imgFilePath = null;
		String imgName = null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(B64);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			if (sel.equals("fromreport")) {
				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
				imgName = "fix_" + name + "_" + timeStamp + ".jpg";
			} else {
				imgName = name + ".jpg";
			}
			imgFilePath = System.getenv("MANAGEMENT_FILES") + "\\" + imgName;
			System.out.println(imgFilePath);
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(imgName);
			} catch (Exception e) {
		}
	}
}
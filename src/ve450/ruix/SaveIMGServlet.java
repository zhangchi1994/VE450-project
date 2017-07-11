package ve450.ruix;

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
		System.out.println("get here");
		String B64 = request.getParameter("data").toString();
		String name = request.getParameter("name").toString();
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(B64);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			System.out.println("pic ready");
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
			String imgFilePath = "C://Users//dell//workspace//VE450-project//WebContent//pic//fix_" + name + "_" + timeStamp + ".jpg";// 新生成的图片
			//String imgFilePath = Server.MapPath("./pic/" + name + ".png");
			//System.out.println(imgFilePath);
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(name);
			} catch (Exception e) {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println(request.getParameter("data").toString());
		String B64 = request.getParameter("data").toString();
		String name = request.getParameter("name").toString();
		System.out.println("to string ready");
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(B64);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			System.out.println("pic ready");
			String imgFilePath = "C://Users//dell//workspace//VE450-project//WebContent//pic//" + name + ".jpg";// 新生成的图片
			System.out.println(imgFilePath);
			//System.out.println(imgFilePath);
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(name);
			} catch (Exception e) {
		}
	}
}
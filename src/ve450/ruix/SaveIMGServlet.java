package ve450.ruix;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//System.out.println(request.getParameter("data").toString());
		String B64 = request.getParameter("data").toString();
		String name = request.getParameter("name").toString();
		System.out.println("to string ready");
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64����
			byte[] b = decoder.decodeBuffer(B64);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// �����쳣����
					b[i] += 256;
				}
			}
			// ����jpegͼƬ
			System.out.println("pic ready");
			String imgFilePath = "C://Users//dell//workspace//VE450-project//WebContent//pic//" + name + ".png";// �����ɵ�ͼƬ
			//System.out.println(imgFilePath);
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			} catch (Exception e) {
		}
	}
}
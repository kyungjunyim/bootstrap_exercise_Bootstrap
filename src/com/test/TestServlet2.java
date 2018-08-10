package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/Upload")
@MultipartConfig(maxFileSize = 1024 * 1024 * 2)
public class TestServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");

		String str = request.getParameter("theAuthor");
		System.out.println(str);
//		File file = new File("c:/data/2.jpg");
//		file.delete();
		Part part = request.getPart("theFile");
		String fileName = getFilename(part);
		fileName = "c:/data/" + fileName;
		if (fileName != null && !fileName.isEmpty()) {
			part.write(fileName);
		}

//		String author = request.getParameter("theAuthor");
//		author = new String(author.getBytes("iso-8859-1"), "EUC-KR");
//
//		response.setContentType("text/html;charset=EUC-KR");
//		PrintWriter out = response.getWriter();
//
//		out.print("�ۼ���:" + author + "<br>");
//		out.print("���ϸ�:<a href='FileDown?file_name=" + fileName + "'>" + fileName + "<br>"); // �ٿ�ε�
//																								// �߰�
//		out.print("����ũ��:" + part.getSize() + "<br>");

	}

	// ���ϸ� ���
	private String getFilename(Part part) {
		String fileName = null;
		String contentDispositionHeader = part.getHeader("content-disposition");
		System.out.println(" part.getHeader :" + part.getHeader("content-disposition"));
		// part.getHeader :form-data; name="theFile";
		// filename="RHDSetup.log" ó�� ����� ���� ���� �����ݷи��� ������ �� �ʿ伺�� ����
		String[] elements = contentDispositionHeader.split(";");

		for (String element : elements) {
			System.out.println("���꽺Ʈ�� ��:" + element);
			fileName = element.substring(element.indexOf('=') + 1);
			System.out.println("Ʈ�� ��:" + fileName);
			fileName = fileName.trim().replace("\"", " "); // " <- �ֵ���ǥ ����
			System.out.println("Ʈ�� ��:" + fileName);
		}
		return fileName;
	}

}

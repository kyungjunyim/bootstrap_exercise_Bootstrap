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
//		out.print("작성자:" + author + "<br>");
//		out.print("파일명:<a href='FileDown?file_name=" + fileName + "'>" + fileName + "<br>"); // 다운로드
//																								// 추가
//		out.print("파일크기:" + part.getSize() + "<br>");

	}

	// 파일명 얻기
	private String getFilename(Part part) {
		String fileName = null;
		String contentDispositionHeader = part.getHeader("content-disposition");
		System.out.println(" part.getHeader :" + part.getHeader("content-disposition"));
		// part.getHeader :form-data; name="theFile";
		// filename="RHDSetup.log" 처럼 헤더가 나옴 따라서 세미콜론마다 지워야 할 필요성이 있음
		String[] elements = contentDispositionHeader.split(";");

		for (String element : elements) {
			System.out.println("서브스트링 전:" + element);
			fileName = element.substring(element.indexOf('=') + 1);
			System.out.println("트림 전:" + fileName);
			fileName = fileName.trim().replace("\"", " "); // " <- 쌍따옴표 지움
			System.out.println("트림 후:" + fileName);
		}
		return fileName;
	}

}

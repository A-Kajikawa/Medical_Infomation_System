package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.PatientBean;

/**
 * 新しい保険証番号と有効期限を更新するためのかますサーブレット
 */
@WebServlet("/KamaseServlet")
public class KamaseServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	HttpSession session = request.getSession();
	PatientBean patientBean = (PatientBean)session.getAttribute("patientBean");
	
	String hokenmei = null;
	Date hokenexp = null;
	try {
		hokenmei = request.getParameter("hokenmei");
		hokenexp = java.sql.Date.valueOf(request.getParameter("hokenexp"));
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	patientBean.setHokenmei(hokenmei);
	patientBean.setHokenexp(hokenexp);
	session.setAttribute("patientBean",patientBean);
	getServletContext().getRequestDispatcher("/changeConfirmationScreen.jsp").forward(request, response);
	 return;
	}
}

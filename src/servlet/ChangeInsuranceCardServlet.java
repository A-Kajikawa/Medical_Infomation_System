
package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.PatientBean;

/**
 * 保険証情報変更用サーブレット
 */
@WebServlet("/ChangeInsuranceCardServlet")
public class ChangeInsuranceCardServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		PatientBean patientBean = (PatientBean)session.getAttribute("patientBean");
		
		String patid = patientBean.getPatid();
		String hokenmei = patientBean.getHokenmei();
		Date hokenexp = patientBean.getHokenexp();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
		}

		String updateInsuranceSql = "update patient set hokenmei = ?, hokenexp = ? where patId = ?;";
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
				PreparedStatement stmt = conn.prepareStatement(updateInsuranceSql)){
				stmt.setString(1, hokenmei);
				stmt.setDate(2, hokenexp);
				stmt.setString(3, patid);
				stmt.executeUpdate();
				RequestDispatcher dispatch = request.getRequestDispatcher("/reception.jsp");
				dispatch.forward(request, response);
				return;
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
}

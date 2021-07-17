package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.PatientArrayBean;
import bean.PatientBean;

/**
 * 保険証期限確認サーブレット
 */
@WebServlet("/ExpirationDateServlet")
public class ExpirationDateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PatientArrayBean patientArrayBean = new PatientArrayBean();

		try{
	      Class.forName("com.mysql.jdbc.Driver");
	    }catch (Exception e) {
	      e.printStackTrace();
	    }

		String dateSql = "SELECT * FROM patient WHERE hokenexp < DATE(NOW());";
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
				PreparedStatement stmt = conn.prepareStatement(dateSql)){
				try(ResultSet rs = stmt.executeQuery()){
					while(rs.next()) {
						PatientBean patientBean = new PatientBean();
						patientBean.setPatid(rs.getString("patid"));
						patientBean.setPatfname(rs.getString("patfname"));
						patientBean.setPatlname(rs.getString("patlname"));
						patientBean.setHokenmei(rs.getString("hokenmei"));
						patientBean.setHokenexp(rs.getDate("hokenexp"));
						patientArrayBean.addPatientArray(patientBean);
						}
					HttpSession session = request.getSession();
					session.setAttribute("patientArrayBean", patientArrayBean);
					getServletContext().getRequestDispatcher("/expiredDisplay.jsp").forward(request, response);
		            return;
				}catch(Exception e) {
					e.printStackTrace();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
}

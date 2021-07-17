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
 * 患者検索サーブレット
 */
@WebServlet("/PatientSerchServlet")
public class PatientSerchServlet extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//PatientArrayBeanの初期化
		PatientArrayBean patientarraybean = new PatientArrayBean();
		String patfname = null;
		
		HttpSession session = request.getSession();
		String flag = (String) session.getAttribute("flag");

		//DB準備をする
		try{
	      Class.forName("com.mysql.jdbc.Driver");
	    }catch (Exception e) {
	      e.printStackTrace();
	    }

		//キーワード検索をする
	    String patientserchsql = "SELECT * FROM patient WHERE patfname like ?;";

		    try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
		        PreparedStatement stmt = conn.prepareStatement(patientserchsql)){
		    	patfname = request.getParameter("patfname");
		        stmt.setString(1, "%"+ patfname +"%");
		        try(ResultSet rs = stmt.executeQuery()){

		          while(rs.next()) {
		          // PatientBeanの初期化
		        	  PatientBean patientbean = new PatientBean();
		        	  patientbean.setPatid(rs.getString("patid"));
		        	  patientbean.setPatfname(rs.getString("patfname"));
		        	  patientbean.setPatlname(rs.getString("patlname"));
		        	  patientbean.setHokenmei(rs.getString("hokenmei"));
		        	  patientbean.setHokenexp(rs.getDate("hokenexp"));
		        	  patientarraybean.addPatientArray(patientbean);
		          }

	            
	            session.setAttribute("patientarraybean", patientarraybean);
	            session.setAttribute("flag", flag);
	            getServletContext().getRequestDispatcher("/patientSearchResultScreen.jsp").forward(request, response);
	            return;
		        }catch(Exception e){
		          e.printStackTrace();
		        }
		      }catch(Exception e){
		        e.printStackTrace();
		      }
	}


}

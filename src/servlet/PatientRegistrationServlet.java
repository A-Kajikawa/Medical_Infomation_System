package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.PatientArrayBean;
import bean.PatientBean;

/**
 * 患者登録用をするサーブレットです。
 *  */
@WebServlet("/PatientRegistrationServlet")
public class PatientRegistrationServlet extends HttpServlet {

	PatientArrayBean patientArrayBean = new PatientArrayBean();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PatientBean patientBean = (PatientBean)session.getAttribute("patientBean");
		System.out.println("患者登録決定サーブレット発動");
		
		//DB準備をする
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
	    }

		//患者情報登録
		 String insertPatientSql = "insert into patient values(?,?,?,?,?);";

		 //DB接続
		 try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
				 PreparedStatement stmt = conn.prepareStatement(insertPatientSql)){
							stmt.setString(1, patientBean.getPatid());
							stmt.setString(2, patientBean.getPatfname());
							stmt.setString(3, patientBean.getPatlname());
							stmt.setString(4, patientBean.getHokenmei());
							stmt.setDate(5, (java.sql.Date) patientBean.getHokenexp());
							// SQLを実行
							stmt.executeUpdate();
							//無事登録が完了したので、管理者画面にディスパッチャー
							getServletContext().getRequestDispatcher("/tourokudekimashita.jsp").forward(request, response);
							return;
					 }catch (Exception e) {
							e.printStackTrace();
					 }
	}
}

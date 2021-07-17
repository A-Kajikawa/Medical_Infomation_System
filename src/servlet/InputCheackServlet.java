package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.PatientBean;
import exception.LoginException;

/**
 * 入力チェック用サーブレット
 */
@WebServlet("/InputCheackServlet")
public class InputCheackServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//セッションの受け取り
		HttpSession session = request.getSession();
		String flag = request.getParameter("flag");
		System.out.println(flag);
		System.out.println("入力チェックサーブレットフェーズ開始");
		//従業員登録入力チェック用
		String empid = null;
		String empfname = null;
		String emplname = null;
		int emprole = 0;
		String radio = null;
		SaltUserPassword hash;
		String hashPassWord = null;
		String emppasswd = null;
		String emppasswd2 = null;
		
		//患者登録入力チェック用
		String patid = null;
		String patfname = null;
		String patlname = null;
		String hokenmei = null;
		Date hokenexp = null;
		
		if(flag.equals("employee")) {
			//従業員登録画面からパラメータ取得
			try {
				empid = request.getParameter("empid");
				empfname = request.getParameter("empfname");
				emplname = request.getParameter("emplname");
				emppasswd = request.getParameter("emppasswd");
				emppasswd2 = request.getParameter("emppasswd2");
				if(emppasswd.equals(emppasswd2)) {
					System.out.println("パスワードok");
				}else {
					LoginException le = new LoginException("ログインに失敗しました");
					session.setAttribute("Except", le);
					RequestDispatcher dispatch = request.getRequestDispatcher("/error2.jsp");
					dispatch.forward(request, response);
					return;
				}
				
				//従業員登録用のラジオボタンの情報を取得("reception" or "doctor")
				radio = request.getParameter("radio");
				System.out.println("フェーズ1.0をクリア");
				//ラジオボタンが受付の場合
					if(radio.equals("reception")){
						emprole = 1;
						//ラジオボタンが医師の場合
					}else{
						emprole = 2;
					}
			}catch(Exception e) {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
				return;
			}
		}else {
			try {
				patid = request.getParameter("patid");
				patfname = request.getParameter("patfname");
				patlname = request.getParameter("patlname");
				hokenmei = request.getParameter("hokenmei");
				hokenexp = java.sql.Date.valueOf(request.getParameter("hokenexp"));
				System.out.println(hokenexp);			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
				
			//DB接続
			try{
				Class.forName("com.mysql.jdbc.Driver");
			}catch (Exception e) {
				e.printStackTrace();
			}

			//IDのチェックを行う
			if(flag.equals("employee")) {
				String empInputSql = "SELECT * FROM employee WHERE empid = ?;";

				try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
						 	PreparedStatement stmt = conn.prepareStatement(empInputSql)){
					stmt.setString(1, empid);
					//もしデーターベースにすでに存在しないなら取得したパスワードをハッシュ化
					try(ResultSet rs = stmt.executeQuery()){
						 if(rs.next()) {
							 System.out.println("フェーズ2.0をクリア");
							 session.setAttribute("flag", flag);
							 LoginException le = new LoginException("ログインに失敗しました");
								session.setAttribute("Except", le);
								RequestDispatcher dispatch = request.getRequestDispatcher("/error2.jsp");
								dispatch.forward(request, response);
								return;
						 }else {
							 System.out.println("フェーズ2.1をクリア");
							 hash = new SaltUserPassword();
							 hashPassWord = hash.getDigest(empid, emppasswd);
							 LoginBean loginBean = new LoginBean();
							 loginBean.setEmpId(empid);
							 loginBean.setEmpFname(empfname);
							 loginBean.setEmpLname(emplname);
							 loginBean.setPassWord(hashPassWord);
							 loginBean.setEmpRole(emprole);
							 session.setAttribute("loginBean", loginBean);
							 getServletContext().getRequestDispatcher("/employeeRegistrationConfirmationScreen.jsp").forward(request, response);
							 return;
						 }
					 }catch(Exception e) {
						 e.printStackTrace();
					 }
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
			}else {
				String patientInputSql = "SELECT * FROM patient WHERE patid = ?;";

				try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
						 	PreparedStatement stmt = conn.prepareStatement(patientInputSql)){
					stmt.setString(1, patid);
					//もしデーターベースにすでに存在しないなら取得したパスワードをハッシュ化
					try(ResultSet rs = stmt.executeQuery()){
						 if(rs.next()) {
							 System.out.println("フェーズ2.5へ移行");
							 flag = "recept";
							 session.setAttribute("flag", flag);
							 LoginException le = new LoginException("ログインに失敗しました");
								session.setAttribute("Except", le);
								RequestDispatcher dispatch = request.getRequestDispatcher("/error2.jsp");
								dispatch.forward(request, response);
								return;
						 }else {
							 System.out.println("フェーズ2.6へ移行");
							 PatientBean patientBean = new PatientBean();
							 patientBean.setPatid(patid);
							 patientBean.setPatfname(patfname);
							 patientBean.setPatlname(patlname);
							 patientBean.setHokenmei(hokenmei);
							 patientBean.setHokenexp(hokenexp);
							 session.setAttribute("patientBean", patientBean);
							 getServletContext().getRequestDispatcher("/patientRegistrationConfirmationScreen.jsp").forward(request, response);
							 return;
						 }
					 }catch(Exception e) {
						 e.printStackTrace();
					 }
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
			}
	}
}

package servlet;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import exception.LoginException;


/**
 * 従業員のパスワード変更をするサーブレットです。
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {

	@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");
		System.out.println(loginBean.getEmpId());
		System.out.println(loginBean.getEmpFname());
		System.out.println(loginBean.getEmpLname());
		System.out.println(loginBean.getPassWord());
		System.out.println(loginBean.getEmpRole());
		String hashPassWord;
		String newPasswd = null;
		String newPasswd2 = null;

		String empid = loginBean.getEmpId();
		newPasswd = request.getParameter("newPasswd");
		newPasswd2 = request.getParameter("newPasswd2");
		
		try {
			
			if(newPasswd.equals("")&&newPasswd2.equals("")) {
				LoginException le = new LoginException("ログインに失敗しました");
				session.setAttribute("Except", le);
				RequestDispatcher dispatch = request.getRequestDispatcher("/error2.jsp");
				dispatch.forward(request, response);
				return;
			}else {
				System.out.println("フェーズ0.5をクリア");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(newPasswd);
		System.out.println(newPasswd2);
		System.out.println("フェーズ１をクリア");
		

		//DB準備をする
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
		}

	  //入力チェック
		if(!empid.equals("1000")) {
			if(newPasswd.equals(newPasswd2)) {
				//入力されたパスワードをハッシュ化
				SaltUserPassword hash = new SaltUserPassword();
				hashPassWord = hash.getDigest(loginBean.getEmpId(), newPasswd);
				System.out.println("フェーズ2をクリア");
			}else {
				System.out.println("フェーズ2.5をクリア");
				LoginException le = new LoginException("ログインに失敗しました");
				session.setAttribute("Except", le);
				RequestDispatcher dispatch = request.getRequestDispatcher("/error2.jsp");
				dispatch.forward(request, response);
				return;
			}
		}else {
			if(!newPasswd.equals(newPasswd2)) {
				LoginException le = new LoginException("ログインに失敗しました");
				session.setAttribute("Except", le);
				RequestDispatcher dispatch = request.getRequestDispatcher("/error2.jsp");
				dispatch.forward(request, response);
				return;
				
			}else {
				System.out.println("フェーズ2.1をクリア");
			}
			hashPassWord = newPasswd;
			System.out.println("フェーズ2.5をクリア");
		}
		
		loginBean.setPassWord(hashPassWord);
		System.out.println("フェーズ３への移行を確認");
		System.out.println(loginBean.getEmpId());
		System.out.println(loginBean.getEmpFname());
		System.out.println(loginBean.getEmpLname());
		System.out.println(loginBean.getPassWord());
		System.out.println(loginBean.getEmpRole());
		session.setAttribute("loginBean", loginBean);
		   
	  //レコードの更新
		  
		  String updatePasswdSql = "update employee set emppasswd = ? where empid = ?;";
		  try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
				  PreparedStatement stmt = conn.prepareStatement(updatePasswdSql)){

			  stmt.setString(1, hashPassWord);
			  stmt.setString(2, loginBean.getEmpId());

			  // SQLを実行
			  stmt.executeUpdate();
			  
			  RequestDispatcher dispatch = request.getRequestDispatcher("/henkoudekimashita.jsp");
		      dispatch.forward(request, response);
		      return;
		  }catch (Exception e) {
			  e.printStackTrace();
		  }
	}
}

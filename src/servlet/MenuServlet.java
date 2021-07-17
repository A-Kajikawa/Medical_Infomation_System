package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
 * ログインの可否を判断し、ログイン権限からそれぞれのメニュー画面へ遷移するサーブレット
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LoginBean loginBean = new LoginBean();
		HttpSession session = request.getSession();
		SaltUserPassword hash;
		String hashPassWord = null;
		String flag = null;

		//パラメータの受け取り
		String empid = request.getParameter("empid");
		String emppasswd = request.getParameter("emppasswd");
		System.out.println(empid);
		System.out.println(emppasswd);
		//nullチェックの例外処理をしています。
		try {
			if(empid.equals("") || emppasswd.equals("")){
				System.out.println("フェーズ1をクリア");
				LoginException le = new LoginException("ログインに失敗しました");
				session.setAttribute("Except", le);
				RequestDispatcher dispatch = request.getRequestDispatcher("/error.jsp");
				dispatch.forward(request, response);
				return;
			}else {
				System.out.println("フェーズ1.5をクリア");
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}

		//管理者権限以外のパスワードはハッシュか
		if(!empid.equals("1000")) {
			hash = new SaltUserPassword();
			hashPassWord = hash.getDigest(empid, emppasswd);
		}else {
			hashPassWord = emppasswd;
		}
		System.out.println("フェーズ２をクリア");

		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
		}

		String loginsql = "SELECT * FROM employee WHERE empid = ? AND emppasswd = ?";

		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
				PreparedStatement stmt = conn.prepareStatement(loginsql)){
			stmt.setString(1, empid);
			stmt.setString(2, hashPassWord);
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
					loginBean.setEmpId(rs.getString("empid"));
					loginBean.setPassWord(rs.getString("empfname"));
					loginBean.setEmpFname(rs.getString("emplname"));
					loginBean.setEmpLname(rs.getString("emppasswd"));
					loginBean.setEmpRole(rs.getInt("emprole"));
					System.out.println("フェーズ３をクリア");
					System.out.println(loginBean.getEmpId());
					System.out.println(loginBean.getEmpFname());
					System.out.println(loginBean.getEmpLname());
					System.out.println(loginBean.getPassWord());
					System.out.println(loginBean.getEmpRole());
				}else {
					LoginException le = new LoginException("ログインに失敗しました");
					session.setAttribute("Except", le);
					getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
					System.out.println("フェーズ４をクリア");
					return;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		//権限によって遷移先分岐
		session.setAttribute("loginBean", loginBean);
		if(loginBean.getEmpRole() == 0){
			RequestDispatcher dispatch = request.getRequestDispatcher("/manager.jsp");
			dispatch.forward(request, response);
		    return;
		}else if(loginBean.getEmpRole() == 1){
			flag = "0";
			session.setAttribute("flag", flag);
			RequestDispatcher dispatch = request.getRequestDispatcher("/reception.jsp");
			dispatch.forward(request, response);
			return;
		}else {
			flag = "1";
			session.setAttribute("flag", flag);
			RequestDispatcher dispatch = request.getRequestDispatcher("/doctor.jsp");
			dispatch.forward(request, response);
			return;
		}
	}
}
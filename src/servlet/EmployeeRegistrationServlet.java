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
 * 従業員登録をするサーブレットです。
 */
@WebServlet("/EmployeeRegistrationServlet")
public class EmployeeRegistrationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		System.out.println(loginBean.getEmpId());
		System.out.println(loginBean.getEmpFname());
		System.out.println(loginBean.getEmpLname());
		System.out.println(loginBean.getPassWord());
		System.out.println(loginBean.getEmpRole());
		System.out.println("登録サーブレットフェーズ開始");
		//DB接続
		 try{
			 Class.forName("com.mysql.jdbc.Driver");
		 }catch (Exception e) {
		     e.printStackTrace();
		 }
		 
		//従業員情報登録
		String insertEmpdataSql = "insert into employee values(?,?,?,?,?);";

		//DB接続
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
				PreparedStatement stmt = conn.prepareStatement(insertEmpdataSql)){
				stmt.setString(1, loginBean.getEmpId());
				stmt.setString(2, loginBean.getEmpFname());
				stmt.setString(3, loginBean.getEmpFname());
				stmt.setString(4, loginBean.getPassWord() );
				stmt.setInt(5, loginBean.getEmpRole() );
				// SQLを実行
				stmt.executeUpdate();
				System.out.println("登録画面へ飛ぶ前フェーズクリア");
				session.setAttribute("loginBean", loginBean);
				//無事登録が完了したので、管理者画面にディスパッチャー
				System.out.println("フェーズ１をクリア");
				getServletContext().getRequestDispatcher("/tourokudekimashita.jsp").forward(request, response);
				return;
		}catch (Exception e) {
				System.out.println("フェーズ１.5をクリア");
			LoginException le = new LoginException("ログインに失敗しました");
			session.setAttribute("Except", le);
			RequestDispatcher dispatch = request.getRequestDispatcher("/error2.jsp");
			dispatch.forward(request, response);
			return;
		}
	}
}
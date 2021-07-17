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

import exception.LoginException;

/**
 * 電話番号をチェックし新しい電話番号に変更するサーブレット
 */
@WebServlet("/ChangeNumberServlet")
public class ChangeNumberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String newPhoneNumber = request.getParameter("newPhoneNumber");
		String newPhoneNumber2 = request.getParameter("newPhoneNumber2");
		String shiireid = (String)session.getAttribute("shiireid");

		//DB準備をする
		 try{
		   Class.forName("com.mysql.jdbc.Driver");
		 }catch (Exception e) {
		   e.printStackTrace();
		 }
		 if(!newPhoneNumber.equals(newPhoneNumber2)) {
			 LoginException le = new LoginException("ログインに失敗しました");
				session.setAttribute("Except", le);
				RequestDispatcher dispatch = request.getRequestDispatcher("/error3.jsp");
				dispatch.forward(request, response);
				return;
			}
		//入力チェック
		  if(newPhoneNumber.equals(newPhoneNumber2)) {

		    //レコードの更新
		    String updatePhoneNumberSql = "update shiiregyosha set shiiretel = ? where shiireid = ?;";
		    try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
		      PreparedStatement stmt = conn.prepareStatement(updatePhoneNumberSql)){

		      stmt.setString(1, newPhoneNumber);
		      stmt.setString(2, shiireid);

		      // SQLを実行
		      stmt.executeUpdate();
		    }catch (Exception e) {
		      e.printStackTrace();
		    }
		    RequestDispatcher dispatch = request.getRequestDispatcher("denwabangouhenkou.jsp");
	      dispatch.forward(request, response);
	      return;
		  }else {
		    RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
	      dispatch.forward(request, response);
	      return;
		  }
	}
}

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

import bean.LoginBean;

/**
 * 従業員検索をするサーブレットです。
 */
@WebServlet("/EmployeeSearchServlet")
public class EmployeeSearchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//LoginArrayBean loginArrayBean = new LoginArrayBean();
		String empid = request.getParameter("empid");

		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
		    e.printStackTrace();
		}

		String EmployeeSerchsql = "SELECT * FROM employee WHERE empid = ?;";
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
		        PreparedStatement stmt = conn.prepareStatement(EmployeeSerchsql)){
		        stmt.setString(1,empid);
		        try(ResultSet rs = stmt.executeQuery()){

		        	if(rs.next()) {
		        	  LoginBean loginBean = new LoginBean();
		        	  loginBean.setEmpId(rs.getString("empid"));
		        	  loginBean.setEmpFname(rs.getString("empfname"));
		        	  loginBean.setEmpLname(rs.getString("emplname"));
		        	  loginBean.setPassWord(rs.getString("emppasswd"));
		        	  loginBean.setEmpRole(rs.getInt("emprole"));
		        	  //loginArrayBean.addLoginArray(loginBean);
		        	  HttpSession session = request.getSession();
		        	  session.setAttribute("loginBean", loginBean);
		        	  getServletContext().getRequestDispatcher("/employeeSearchResultScreen.jsp").forward(request, response);
		        	  return;
		          }else {
		        	  getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
		        	  return;
		          }
		        }catch(Exception e){
		          e.printStackTrace();
		        }
		      }catch(Exception e){
		        e.printStackTrace();
		      }
	}
}

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


import bean.ShiiregyoshaArrayBean;
import bean.ShiiregyoshaBean;

/**
 * 指定以上の資本金検索をするサーブレットです
 */
@WebServlet("/CapitalSearchServlet")
public class CapitalSearchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int shihonkin = 0;
		HttpSession session = request.getSession();
		ShiiregyoshaArrayBean shiiregyoshaArray = new ShiiregyoshaArrayBean();
		try {
			shihonkin = Integer.parseInt(request.getParameter("shihonkin"));
			
			System.out.println(shihonkin);
		}catch(NumberFormatException e) {
		      getServletContext().getRequestDispatcher("/error.jsp");
		      return;
		}
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
		    e.printStackTrace();
		}
		String capitalSerchSql = "SELECT * FROM shiiregyosha WHERE shihonkin >= ?;";
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
		        PreparedStatement stmt = conn.prepareStatement(capitalSerchSql)){
		        stmt.setInt(1,shihonkin);
		        try(ResultSet rs = stmt.executeQuery()){
		          while(rs.next()) {
		          ShiiregyoshaBean shiiregyoshaBean = new ShiiregyoshaBean();
		          shiiregyoshaBean.setShireid(rs.getString("shiireid"));
		          shiiregyoshaBean.setShiiremei(rs.getString("shiiremei"));
		          shiiregyoshaBean.setShiireaddress(rs.getString("shiireaddress"));
		          shiiregyoshaBean.setShiiretel(rs.getString("shiiretel"));
		          shiiregyoshaBean.setShihonkin(rs.getInt("shihonkin"));
		          shiiregyoshaBean.setNouki(rs.getInt("nouki"));
		          shiiregyoshaArray.addShiiregyoshaArray(shiiregyoshaBean);
		          }
		          if(shiiregyoshaArray.getArraySize()==0) {
	              getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
	              return;
	            }else {
	            	
	            	session.setAttribute("shiiregyoshaArray", shiiregyoshaArray);
	            	int id = 1;
	            	session.setAttribute("id", id);
	            getServletContext().getRequestDispatcher("/shiiregyoshaSearchResultScreen.jsp").forward(request, response);
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

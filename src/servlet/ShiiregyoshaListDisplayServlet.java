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

import bean.ShiiregyoshaArrayBean;
import bean.ShiiregyoshaBean;

/**
 * 仕入れ業者を一覧表示するサーブレットです
 */
@WebServlet("/ShiiregyoshaListDisplayServlet")
public class ShiiregyoshaListDisplayServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ShiiregyoshaArrayBean shiiregyoshaArray = new ShiiregyoshaArrayBean();
		HttpSession session = request.getSession();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
		}
		String listDisplaySql = "select * from shiiregyosha;";
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
				PreparedStatement stmt = conn.prepareStatement(listDisplaySql)){
				//SQLを実行
				try(ResultSet rs = stmt.executeQuery()){
					while(rs.next()) {
						ShiiregyoshaBean shiiregyoshaBean = new ShiiregyoshaBean();
						//取得情報をBeanに保存
						shiiregyoshaBean.setShireid(rs.getString("shiireid"));
						shiiregyoshaBean.setShiiremei(rs.getString("shiiremei"));
						shiiregyoshaBean.setShiireaddress(rs.getString("shiireaddress"));
						shiiregyoshaBean.setShiiretel(rs.getString("shiiretel"));
						shiiregyoshaBean.setShihonkin(rs.getInt("shihonkin"));
						shiiregyoshaBean.setNouki(rs.getInt("nouki"));
						shiiregyoshaArray.addShiiregyoshaArray(shiiregyoshaBean);
						session.setAttribute("shiiregyoshaArray", shiiregyoshaArray);
						int id = 0;
						session.setAttribute("id", id);
						RequestDispatcher dispatch = request.getRequestDispatcher("/shiiregyoshaSearchResultScreen.jsp");
						dispatch.forward(request, response);
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

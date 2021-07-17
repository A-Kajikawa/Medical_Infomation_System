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
 * 仕入れ業者を検索するサーブレット
 */
@WebServlet("/ShiiregyoshaSearchServlet")
public class ShiiregyoshaSearchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//仕入れ業者検索画面からパラメータ取得
		String shiireid = request.getParameter("shiireid");

		ShiiregyoshaArrayBean shiiregyoshaArray = new ShiiregyoshaArrayBean();
		//セッション生成
		HttpSession session = request.getSession();

		 //DB接続準備
		 try{
		      Class.forName("com.mysql.jdbc.Driver");
		 }catch (Exception e) {
		      e.printStackTrace();
		 }
		//検索をする
		String shiiregyoshaSelectsql = "SELECT * FROM shiiregyosha WHERE shiireid = ?;";

		//DB接続
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
			PreparedStatement stmt = conn.prepareStatement(shiiregyoshaSelectsql)){
			stmt.setString(1, shiireid);
			//SQLを実行
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
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
				}else {
					RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
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

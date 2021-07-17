package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ShiiregyoshaBean;

/**
 * 仕入れ業者の電話番号を変更するサーブレット
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    
		HttpSession session = request.getSession();
		
		
    
		//仕入れ業者用
		String shiireid = "";
		shiireid = request.getParameter("shiireid");
		System.out.println(shiireid);
    
		//DB接続
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    	}catch (Exception e) {
    		e.printStackTrace();
    	}

    	
    	//仕入れ業者の電話番号変更
    	
    	String shiireidsql = "SELECT * FROM shiiregyosha WHERE shiireid = ?;";

    	try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
    			PreparedStatement stmt = conn.prepareStatement(shiireidsql)){
    		stmt.setString(1, shiireid);
    		try(ResultSet rs = stmt.executeQuery()){
    			if(rs.next()){
    				//検索結果がある場合は商品情報を取得
    				ShiiregyoshaBean shiiregyoshaBean = new ShiiregyoshaBean();
    				shiiregyoshaBean.setShireid(rs.getString("shiireid"));
    				shiiregyoshaBean.setShiiremei(rs.getString("shiiremei"));
    				shiiregyoshaBean.setShiireaddress(rs.getString("shiireaddress"));
    				shiiregyoshaBean.setShiiretel(rs.getString("shiiretel"));
    				shiiregyoshaBean.setShihonkin(rs.getInt("shihonkin"));
    				shiiregyoshaBean.setNouki(rs.getInt("nouki"));
    				   				
    				session.setAttribute("shiiregyoshaBean", shiiregyoshaBean);
    				getServletContext().getRequestDispatcher("/changePhoneNumber.jsp").forward(request, response);
    				return;
    				}else{
    				//検索結果がない場合は例外を自分で発生させてエラーにする
    				throw new SQLException("エラー画面");
    				}
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    		}catch(Exception e){
    			e.printStackTrace();
    		}
	}
}








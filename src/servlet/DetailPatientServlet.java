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

import bean.PatientBean;

/**
 * 患者検索画面の結果から受付なら保険証確認画面へ医者なら投薬指示画面へディスパッチャー
 */
@WebServlet("/DetailPatientServlet")
public class DetailPatientServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String flag = "";
		//受付ならflag0が渡ってくる。医者ならflag1がわたってくる
		flag = (String) session.getAttribute("flag");	
		//患者検索用
		String patid = "";
		patid = request.getParameter("patid");
		System.out.println(patid);
		
		//DB接続
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	//患者検索用
    	String patidsql = "SELECT * FROM patient WHERE patid = ?;";

    	try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
    			PreparedStatement stmt = conn.prepareStatement(patidsql)){
    		stmt.setString(1, patid);
    		try(ResultSet rs = stmt.executeQuery()){
    			if(rs.next()){
    				//検索結果がある場合は患者情報を取得
    				PatientBean patientBean = new PatientBean();
    				patientBean.setPatid(rs.getString("patid"));
    				patientBean.setPatfname(rs.getString("patfname"));
    				patientBean.setPatlname(rs.getString("patlname"));
    				patientBean.setHokenmei(rs.getString("hokenmei"));
    				patientBean.setHokenexp(rs.getDate("hokenexp"));
    				session.setAttribute("patientBean", patientBean);
    					if(flag.equals("0")) {
    						getServletContext().getRequestDispatcher("/insuranceCardInformationChangeScreen.jsp").forward(request, response);
    						return;
    					}else if(flag.equals("1")) {
    						getServletContext().getRequestDispatcher("/treatmentScreen.jsp").forward(request, response);
    						return;
    					}
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

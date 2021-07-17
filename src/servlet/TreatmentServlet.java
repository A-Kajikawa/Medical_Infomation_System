package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.PatientBean;
import bean.TouyakuArrayBean;
import bean.TouyakuBean;

/**
 * 投薬指示を確定するサーブレットです
 */
@WebServlet("/TreatmentServlet")
public class TreatmentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PatientBean patientBean = (PatientBean)session.getAttribute("patientBean");
		TouyakuArrayBean touyakuarray = (TouyakuArrayBean)session.getAttribute("touyakuArray");
		
		System.out.println(touyakuarray.getArraySize());
			
		int i = 4;
		
		//DB接続
		 try{
			 Class.forName("com.mysql.jdbc.Driver");
		 }catch (Exception e) {
		     e.printStackTrace();
		 }
		 
		//処置情報確定し、テーブルにデータを挿入
		 String insertshochidataSql = null;
		 if(touyakuarray.getArraySize() == 4) {
			 insertshochidataSql = "insert into shochi values(?,?,?,?,?,?,?,?,?,?,?);";
		 }else if(touyakuarray.getArraySize() == 3) {//(patid, patfname, patlname, medicine1, quantity1, medicine2, quantity2, medicine3, quantity3, medicine4, quantity4)
			 insertshochidataSql = "insert into shochi values(?,?,?,?,?,?,?,?,?,\'null\',\'null\');";
		 }else if(touyakuarray.getArraySize() == 2) {
			 insertshochidataSql = "insert into shochi values(?,?,?,?,?,?,?,\'null\',\'null\',\'null\',\'null\');";
		 }else if(touyakuarray.getArraySize() == 1) {
			 insertshochidataSql = "insert into shochi values(?,?,?,?,?,\'null\',\'null\',\'null\',\'null\',\'null\',\'null\');";
		 }else if(touyakuarray.getArraySize() == 0) {
			 insertshochidataSql = "insert into shochi values(?,?,?,\'null\',\'null\',\'null\',\'null\',\'null\',\'null\',\'null\',\'null\');";
		 }
		 System.out.println(insertshochidataSql);
		 
		 try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s3a124", "root","password");
				 PreparedStatement stmt = conn.prepareStatement(insertshochidataSql)){
			 stmt.setString(1, patientBean.getPatid());
			 stmt.setString(2, patientBean.getPatfname());
			 stmt.setString(3, patientBean.getPatlname());
			 	for(TouyakuBean p: touyakuarray.getTouyakuArray()) {
			 		
			 		stmt.setString(i, p.getMedicine());
			 			i++;
					stmt.setString(i, p.getQuantity());
						i++;
					}
			 	// SQLを実行
				stmt.executeUpdate();
				//無事登録が完了したので、医師画面にディスパッチャー
				getServletContext().getRequestDispatcher("/shochikanryoshimashita.jsp").forward(request, response);
				return;
		}catch (Exception e) {
			 e.printStackTrace();
		}
	}
}

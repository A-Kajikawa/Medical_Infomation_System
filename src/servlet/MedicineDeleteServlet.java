package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.TouyakuArrayBean;

/**
 * 投薬指示を削除(キャンセル)するサーブレットです。
 */
@WebServlet("/MedicineDeleteServlet")
public class MedicineDeleteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//削除操作したのち確認画面へディスパッチャー
		HttpSession session = request.getSession();
		TouyakuArrayBean touyakuArray = (TouyakuArrayBean)session.getAttribute("touyakuArray");
		String medicine = request.getParameter("medicine");
		touyakuArray.delTouyakuArray(medicine);
		session.setAttribute("touyakuArray ", touyakuArray);
	    getServletContext().getRequestDispatcher("/touyakushijiConfirmationScreen.jsp").forward(request, response);
		
	}

}

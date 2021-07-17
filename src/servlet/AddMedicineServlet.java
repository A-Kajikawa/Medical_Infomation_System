package servlet;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class AddMedicineServlet
 */
@WebServlet("/AddMedicineServlet")
public class AddMedicineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			PatientBean patientBean = (PatientBean) session.getAttribute("patientBean");
			TouyakuArrayBean touyakuArray = (TouyakuArrayBean) session.getAttribute("touyakuArray");
			ArrayList<TouyakuBean> array = touyakuArray.getTouyakuArray();
			String medicine = request.getParameter("medicine");
			String quantity = request.getParameter("quantity");
			TouyakuBean bean = new TouyakuBean();
			bean.setMedicine(medicine);
			bean.setQuantity(quantity);
			int size = touyakuArray.getArraySize();
			array.add(size,bean);
			
			session.setAttribute("patientBean", patientBean);
			session.setAttribute("touyakuArray", array);
			getServletContext().getRequestDispatcher("/touyakushijiConfirmationScreen.jsp").forward(request, response);
			return;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
//<form action="AddMedicineServlet" method="get">
//薬名<input type="text" name="medicine"><br>
//数量<input type="text" name="quantity"><br>
//<input type="submit" value="追加"></form>
//<br><br>
//<input type="button" value="処置確定"
//			onclick="location.href='TreatmentServlet'" />
//<!--  <form action="TreatmentServlet" method="get">
//<input type="submit" value="処置確定">
//</form>

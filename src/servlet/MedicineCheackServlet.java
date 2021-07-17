package servlet;

import java.io.IOException;

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
 * 投薬指示画面の入力チェック用サーブレット
 */
@WebServlet("/MedicineCheackServlet")
public class MedicineCheackServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PatientBean patientBean = (PatientBean) session.getAttribute("patientBean");
		//TouyakuArrayBean touyakuArray = (TouyakuArrayBean) session.getAttribute("touyakuArray");
		String medicine1;
		String medicine2;
		String medicine3;
		String other;
		String otherInput = null;
		
		//if(touyakuArray == null) {//セッション内に情報がない=初回
		TouyakuArrayBean touyakuArray = new TouyakuArrayBean();//情報をnewする
			try {
				medicine1 = request.getParameter("medicine1");
				if(medicine1.equals("1")) {
					TouyakuBean touyakuBean = new TouyakuBean();
					//投薬指示ありArrayListへ薬名と量をそれぞれ代入
					touyakuBean.setMedicine("エスタックイブファインEX");
					touyakuBean.setQuantity(request.getParameter("medicine1_quantity1"));
					touyakuArray.addTouyakuArray(touyakuBean);
				}
				medicine2 = request.getParameter("medicine2");
				if(medicine2.equals("1")) {
					TouyakuBean touyakuBean = new TouyakuBean();
					touyakuBean.setMedicine("パブロンエースPro錠");
					touyakuBean.setQuantity(request.getParameter("medicine2_quantity2"));
					touyakuArray.addTouyakuArray(touyakuBean);
				}
				medicine3 = request.getParameter("medicine3");
				if(medicine3.equals("1")) {
					TouyakuBean touyakuBean = new TouyakuBean();
					touyakuBean.setMedicine("パブロンエースPro微粒");
					touyakuBean.setQuantity(request.getParameter("medicine3_quantity3"));
					touyakuArray.addTouyakuArray(touyakuBean);
				}
			
				other = request.getParameter("other");
				if(other.equals("1")) {
					try {
						otherInput = request.getParameter("otherInput");
						System.out.println(otherInput);
					}catch(NullPointerException e) {
						throw e;
					}
				}
				if(!otherInput.equals(null)) {
					TouyakuBean touyakuBean = new TouyakuBean();
					touyakuBean.setMedicine(otherInput);
					touyakuBean.setQuantity(request.getParameter("otherQuantity"));
					
					touyakuArray.addTouyakuArray(touyakuBean);
				}

			}catch(Exception e) {
				e.printStackTrace();
			}
//		}else {//投薬量を変更した場合。2回目。要はnullじゃなかった時、
//		//TouyakuBean型のdel作成
//		TouyakuBean del = null;
//		//TouyakuBeanオブジェクトの入ったArrayList,TouyakuArrayBeanから1件ずつ取り出す
//		for (TouyakuBean rec : touyakuArray.getTouyakuArray()) {
//            if (rec.getQuantity().equals("エスタックイブファインEX")) {
//            	rec.setQuantityMg(request.getParameter("medicine1_quantity1"));
//                break;
//                
//            }
//			if (del != null) {//2回目時は今までの数量に新たに数量を追加し、
//                cartbean.setQuantity(quantity+del.getQuantity());
//                //古いほうのCartArrayBeanへ入っているデータを消去
//                cartArrayBean.getCartArray().remove(del);
//              }else {//1回目時はそのまま追加するだけでよい
//                cartbean.setQuantity(quantity);
//              }
//
//              //2回目以降は数量以外は変化なくcartbeanに値が入っているのでそれをCartArrayBeanへ入れる
//              cartArrayBean.addCartArray(cartbean);
//              session.setAttribute("cartArrayBean", cartArrayBean);//セッションに登録する
//              getServletContext().getRequestDispatcher("/shohin_cart.jsp").forward(request, response);
//      
        //}
		
		
		
		
		session.setAttribute("touyakuArray", touyakuArray);
		session.setAttribute("patientBean", patientBean);
		getServletContext().getRequestDispatcher("/touyakushijiConfirmationScreen.jsp").forward(request, response);
		return;
	}
	

}

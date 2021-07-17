package bean;

import java.io.Serializable;
import java.util.ArrayList;


public class TouyakuArrayBean implements Serializable {

	public TouyakuArrayBean() {
	}

	ArrayList<TouyakuBean> touyakuArray = new ArrayList<>();
	
	public ArrayList<TouyakuBean> getTouyakuArray(){
		return touyakuArray;
	}

	public void addTouyakuArray(TouyakuBean obj) {
		touyakuArray.add(obj);
	}

	public int getArraySize() {
		return touyakuArray.size();
	}
	public void delTouyakuArray(String medicine) {
	    TouyakuBean del = null;
	  for (TouyakuBean rec : touyakuArray) {
	              if (rec.getQuantity().equals(medicine)) {
	                  del = rec; //ここで削除するとループ中に要素数が変わり例外になる可能性あり
	                  break;
	              }
	          }
	          if (del != null) {
	        	  touyakuArray.remove(del); //ループの外で削除すれば安全
	          }
	  }

}

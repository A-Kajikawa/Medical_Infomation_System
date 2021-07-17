package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ShiiregyoshaArrayBean implements Serializable {

	public ShiiregyoshaArrayBean() {
	}
	private ArrayList<ShiiregyoshaBean> shiiregyoshaArray = new ArrayList<ShiiregyoshaBean>();
	
	public  ArrayList<ShiiregyoshaBean> getShiiregyoshaArray(){
		return shiiregyoshaArray;
	}

	public void addShiiregyoshaArray(ShiiregyoshaBean obj) {
		shiiregyoshaArray.add(obj);
	}
	public int getArraySize(){
	    return shiiregyoshaArray.size();
	  }

}

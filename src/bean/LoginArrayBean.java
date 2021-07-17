package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class LoginArrayBean implements Serializable{

	//大域変数配列宣言
	private ArrayList<LoginBean>loginArray = new ArrayList<LoginBean>();

	public LoginArrayBean() {
	}

	public ArrayList<LoginBean> getLoginArray(){
		return loginArray;
	}

	public void addLoginArray(LoginBean obj) {
		loginArray.add(obj);
	}

	public int getArraySize() {
		return loginArray.size();
	}

}

package bean;

import java.io.Serializable;

public class TouyakuBean implements Serializable {

	public TouyakuBean() {
	}
	
	String medicine = null;
	String quantity = null;
	
	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}

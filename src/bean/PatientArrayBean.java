package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class PatientArrayBean implements Serializable{

	//大域変数配列宣言
	private ArrayList<PatientBean> patientArray = new ArrayList<PatientBean>();

	public PatientArrayBean() {
	}

	public  ArrayList<PatientBean> getPatientArray(){
		return patientArray;
	}

	public void addPatientArray(PatientBean obj) {
		patientArray.add(obj);
	}

}

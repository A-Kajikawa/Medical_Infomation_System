package bean;

import java.sql.Date;

public class PatientBean {

	public PatientBean() {
	}
	String patid = null;
	String patfname = null;
	String patlname = null;
	String hokenmei = null;
	Date hokenexp = null;
	public String getPatid() {
		return patid;
	}
	public void setPatid(String patid) {
		this.patid = patid;
	}
	public String getPatfname() {
		return patfname;
	}
	public void setPatfname(String patfname) {
		this.patfname = patfname;
	}
	public String getPatlname() {
		return patlname;
	}
	public void setPatlname(String patlname) {
		this.patlname = patlname;
	}
	public String getHokenmei() {
		return hokenmei;
	}
	public void setHokenmei(String hokenmei) {
		this.hokenmei = hokenmei;
	}
	public Date getHokenexp() {
		return hokenexp;
	}
	public void setHokenexp(Date hokenexp) {
		this.hokenexp = hokenexp;
	}

}

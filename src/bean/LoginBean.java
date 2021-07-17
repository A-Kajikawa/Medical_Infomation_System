package bean;

import java.io.Serializable;

public class LoginBean implements Serializable{

  public LoginBean() {}

  private String empId;
  private String passWord;
  private String empFname;
  private String empLname;
  private int empRole;


  public String getEmpFname() {
    return empFname;
  }
  public void setEmpFname(String empFname) {
    this.empFname = empFname;
  }
  public String getEmpLname() {
    return empLname;
  }
  public void setEmpLname(String empLname) {
    this.empLname = empLname;
  }
  public int getEmpRole() {
    return empRole;
  }
  public void setEmpRole(int empRole) {
    this.empRole = empRole;
  }
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String userId) {
    this.empId = userId;
  }
  public String getPassWord() {
    return passWord;
  }
  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }


}
package edu.kh.jdbc1.model.vo;

import java.sql.Date;

public class em {
	
	
	 private String empName;
	 private Date hireDate;
	 private char Gender;
	 
	 public em() {}
	 
	 

	

	public em(String empName, Date hireDate, char gender) {
		super();
		this.empName = empName;
		this.hireDate = hireDate;
		this.Gender = gender;
	}
	
	





	public String getEmpName() {
		return empName;
	}





	public void setEmpName(String empName) {
		this.empName = empName;
	}





	public Date getHireDate() {
		return hireDate;
	}





	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}





	public char getGender() {
		return Gender;
	}





	public void setGender(char gender) {
		this.Gender = gender;
	}


//
//
//
//	@Override
//	public String toString() {
//		return  empName + " / " + hireDate + " / " + Gender ;
//	}
//	 
//	 
	

}

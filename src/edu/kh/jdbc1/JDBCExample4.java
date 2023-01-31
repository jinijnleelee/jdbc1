package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Emp;
import edu.kh.jdbc1.model.vo.Employee;

public class JDBCExample4 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		// 직급명,급여 입력받아
//		해당 직급에서 입력 받은 급여보다 많이 받는 사원의
//		이름, 직급명, 급여 ,연봉을 조회하여 출력
//		단, 조회 결과가 없으면 "조회결과 없음" 출력
//		
//		조회 결과가 있으면 아래와 같이 출력
//		선동일 / 대표 / 8000000 / 96000000
//		송종기 / 부장 / 6000000 / 72000000
//		//....
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			System.out.println("직급명 입력" );
			String inputJObName  = sc.next();
			
			System.out.println("급여 입력" );
			int inputSalary  = sc.nextInt();
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버의 종류
			
				String ip = "localhost"; // DB 서버 컴퓨터 IP
				// localhost == 127.0.0.1 (loop back ip)
			
			
				
				String port = ":1521"; // 포트번호 1521 (기본값)
				
				String sid = ":XE"; // DB 이름
				
				String user = "kh";
				
				String pw = "kh1234";
				
				conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
				
				
				String sql = "SELECT EMP_NAME, JOB_NAME, SALARY, SALARY * 12 ANNUAL_INCOME "
			
						+ " FROM EMPLOYEE "
						+ " JOIN JOB USING(JOB_CODE) "
						+ " WHERE JOB_NAME = '" + inputJObName + "'"
						+ " AND SALARY > " + inputSalary;
				
				
				stmt = conn.createStatement();
			
				
				//Statement 객체를 이용하여
				//SQL(SELECT)을 db에 전달하여 실행한 후
				//Result set 을 반환받아 rs 변수에 대입 
				rs = stmt.executeQuery(sql);
				
				List<Employee> list = new ArrayList<>();
				
				
				while(rs.next()) {
					
					String empName = rs.getString("EMP_NAME");
					String jobName = rs.getString("JOB_NAME");
					int salary = rs.getInt("SALARY");
					int annualIncome = rs.getInt("ANNUAL_INCOME");
					
					list.add(new Employee(empName,jobName,salary,annualIncome));
					
					
					
					
				}
			
				
				
	if(list.isEmpty()) {//List가 비어있을경우 
					
					//isEmpty() : 비어있으면 true
					
					System.out.println("조회결과없습니다");
				}else {
					
					//향상된 for문 
					for(Employee emp : list)
						System.out.println(emp);
					
					
				}
	
	
		}catch(ClassNotFoundException e) {
			
			
			e.printStackTrace();
		}catch(SQLException e) {
			
			
			e.printStackTrace();
		}finally {
			try {
				if( rs != null ) rs.close();
				if( stmt != null ) stmt.close();
				if( conn != null ) conn.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}

package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import edu.kh.jdbc1.model.vo.em;

public class JDBCExample5 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// TODO Auto-generated method stub
		//입사일을 입력("2022-09-06") 받아
		//입력 받은 값보다 먼저 입사한 사람의
		//이름,입사일,성별(M,F)조회
		
		
	
		
//		01) 선동일 / 1990년 02월 06일 / M
//		02) 송은희 / 1994년 05월 03일 / F
//		03) 정중하 / 1999년 09월 09일 / M

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			
			System.out.println("입사일 : ");
			String inputHireDate = sc.next();
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버의 종류
			
				String ip = "localhost"; // DB 서버 컴퓨터 IP
				// localhost == 127.0.0.1 (loop back ip)
			
			
				
				String port = ":1521"; // 포트번호 1521 (기본값)
				
				String sid = ":XE"; // DB 이름
				
				String user = "kh";
				
				String pw = "kh1234";
				
	conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
				
				
				String sql = "SELECT EMP_NAME 이름,TO_CHAR(HIRE_DATE,'YYYY\"년\"MM\"월\"DD\"일\"') 입사일, "
						+ " DECODE(SUBSTR(EMP_NO,8,1),'1','M','2','F') 성별 "
						+ " FROM EMPLOYEE "
						+ " WHERE HIRE_DATE < TO_DATE( '" +  inputHireDate + "')";
				
				//문자열 내부에 쌍따움표 작성시 \"로 작성해야 한다 ! (익스케이스문자 )
				
				stmt = conn.createStatement();
			
				
				//Statement 객체를 이용하여
				//SQL(SELECT)을 db에 전달하여 실행한 후
				//Result set 을 반환받아 rs 변수에 대입 
				rs = stmt.executeQuery(sql);
				
				List<em> list = new ArrayList<>();
				
				
				while(rs.next()) {
					
					
					
					em emp = new em();
					
					
					emp.setEmpName(rs.getString("이름"));//조회시 컬럼명이 "이름"
					emp.setHireDate(rs.getString("입사일")); //조회시 컬럼명이 "입사일"
					//현재행의 "입사일" 컬럼의 문자열 값을 얻어와
					//emp 가 참조하는 객체의 hireDate필드에 세팅
					emp.setGender(rs.getString("성별").charAt(0));
					
					//-> char 자료형 매개변수 필요
					//"중요!"
					//java의 char 문자 1개 의미
					//DB의 CHAR 고정길이 문자열 (==String)
					//DB 칼럼값을 char 자료
					//String.charAt(index) 이용!
					
					
					
				
					//list 에 emp 객체 추가 
					list.add(emp);
					
					
					
					
					
				}
			
				
				
//	if(list.isEmpty()) {//List가 비어있을경우 
//					
//					//isEmpty() : 비어있으면 true
//					
//					System.out.println("조회결과없습니다");
//				}else {
//					
//					//향상된 for문 
//					for(em em : list)
//						System.out.println(em);
				
				if(list.size() == 0) {
					System.out.println("조회결과없습니다");
				}else {
					
				//일반 for문 
					for(int i = 0; i< list.size();i++) {
						System.out.printf("%02d) %s / %s / %c\n",
						i+1,
						list.get(i).getEmpName(),
						list.get(i).getHireDate(),
						list.get(i).getGender()
						);
						
					}
					
					
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

				
	
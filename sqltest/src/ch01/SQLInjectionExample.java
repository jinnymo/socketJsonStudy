package ch01;

import java.sql.Connection;

public class SQLInjectionExample {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try (Connection conn = DBConnectionManager.getConnection()){
			
			java.util.Scanner sc = new java.util.Scanner(System.in);
			System.out.println("사용자 이름 입력 하세요");
			String username = sc.nextLine();
			
			String query = " SELECT * FROM user WHERE username = "+ username +" ";
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}

}

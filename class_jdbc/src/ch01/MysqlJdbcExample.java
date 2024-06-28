package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch01.dto.Employee;

public class MysqlJdbcExample {

	public static void main(String[] args) {

		// 준비
		//
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root";// 상용서비에서 절대 루트 계정 사용금지
		String password = "asd123";

		// 필용 데이터 타입
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		// 1. mysql 구현체를 사용하겠다는 설정을 해야 한다.
		//jdbc 드라이버 로드(Mysql 구현 클래스 로드)
		try {
			//1.메모리에 사용하는 드라이버(jdbc api를 구현한 클래스) 클래스를 띄운다.
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2. 데이터 베이스에 연결 설정
			connection = DriverManager.getConnection(url,user,password);
			//3. sql 실행.0
			statement = connection.createStatement();
			String name = "1 or 1=1";
			resultSet = statement.executeQuery("select*from employee where id ="+ name); // select 사용시
			// 구문 분석 -- 파싱
			
			String query = "select*from employee where id = ?";
		//PreparedStatement preparedStatement = connection.prepareStatement(query);
		//	preparedStatement.setString(1,"1 or 1=1");
//			preparedStatement.setString(2,"홍길동");
//			preparedStatement.setString(3,"IT");
//			preparedStatement.setString(4,"40000000");
			//resultSet = preparedStatement.executeQuery();
///			int rowCount = preparedStatement.executeUpdate();
			
			//System.out.println("rowCount : "+ rowCount);
			//4. 결과 처리
			List<Employee> list = new ArrayList<>();
			StringBuffer sb = new StringBuffer();
			while(resultSet.next()) {
				System.out.println(resultSet);
				Employee employee =  new Employee();
				employee.add(resultSet);
				list.add(employee);
				//employee.setId(id);
				// .... 
				
				list.add(employee);
				
				
				System.out.println("USER ID : "+ resultSet.getInt("id"));
				System.out.println("USER NAME : "+ resultSet.getString("name"));
				System.out.println("USER DEPARTMENT : "+ resultSet.getString("department"));
				System.out.println("----------------------");
			}
			
			for (Employee employee : list) {
				System.out.println(employee.getName());
				
			}
			// statement.executeUpdate(password); --> insert, update, delete 사용
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateExample {

	//Connection 객체를 얻어서 insert 구문을 직접 만들어 보세요
	//mydb2 , employee 테이블에 값을 넣는 코드 작성
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			String query = "update employee set salary\r\n "
					+ "= ? where id = ?;";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "50000000");
			preparedStatement.setInt(2, 3);
			int rowCount = preparedStatement.executeUpdate();
			System.out.println("rowCount : " + rowCount);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}

package ch04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionExample {

	public static void main(String[] args) {
		
		//드라이버
		String url = "jdbc:mysql://localhost:3306/m_board?serverTimezone=Asia/Seoul";
		String id = "root";
		String password = "asd123";
		
		//구현체를 사용
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		
		
			try (Connection conn = DriverManager.getConnection(url,id,password)){
				conn.setAutoCommit(false);
				String sqlInsert = " INSERT INTO user(username,password,email,userRole,address,createDate) VALUES "
						+ " (?,?,?,?,?,now()) ";
				PreparedStatement psmt1 = conn.prepareStatement(sqlInsert);
				psmt1.setString(1,"김철수");
				psmt1.setString(2,"asd123");
				psmt1.setString(3,"jinny1105@gmail.com");
				psmt1.setString(4,"user");
				psmt1.setString(5,"부산시 해운대구");
				psmt1.executeUpdate();
				
				psmt1.setString(1,"김철수");
				psmt1.setString(2,"asd123");
				psmt1.setString(3,"jinny1105@gmail.com");
				psmt1.setString(4,"user");
				psmt1.setString(5,"부산시 해운대구");
				psmt1.executeUpdate();
				String sqlUpdate = "UPDATE user SET email =? WHERE username =?";
				PreparedStatement psmt2 = conn.prepareStatement(sqlUpdate);
				psmt2.setString(1, "b@naver.com");
				psmt2.setString(2, "김유신");
				psmt2.executeUpdate();
				
				conn.commit();
			} catch (Exception e) {
				//conn.rollback();
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

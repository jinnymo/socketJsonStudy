package ch05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batch {
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/demo3?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";
		Connection conn =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
			conn.setAutoCommit(false);
			String sql =" INSERT INTO user(name,email) VALUES(?,?) ";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, "유저5");
			ps.setString(2, "유저1@nate.com");
		
			//배치 처리하기위한 코드 필요
			ps.addBatch();
			
			ps.setString(1, "유저6");
			ps.setInt(2, 5);
			ps.addBatch();
			
			int[] rowConts = ps.executeBatch();
			if(rowConts.length >= 0) {
				conn.rollback();
			} else {
				conn.commit();
			}
			System.out.println(rowConts.length);
		} catch (Exception e) {
			// TODO: handle exception
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	
	}
}

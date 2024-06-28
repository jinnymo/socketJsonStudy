package ch01.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//dto 설계
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

	private int id;
	private String name;
	private String department;
	private double salary;
	private String hire_date;
	
	public void add(ResultSet resultSet) {
		try {
			id = resultSet.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}

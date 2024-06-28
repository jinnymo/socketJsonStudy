package ver2;

import java.sql.SQLException;
import java.util.List;

import ver2.model.StudentDTO;

public class StudentManagementSystem {

	private static final StudentDAO STUDENTDAO = new StudentDAO();
	
	public static void main(String[] args) {
		try {
			List<StudentDTO> list = STUDENTDAO.getAllStudents();
			System.out.println(list.size());
			System.out.println(list.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

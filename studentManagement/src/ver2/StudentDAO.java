package ver2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ver2.model.StudentDTO;

//물론 기능설계는 인터페이스를 먼저 작성하고 구현 클래스를 만드는것이 좋다.
public class StudentDAO {

	// 학생 정보 추가 기능 만들기
	public void addStudent(StudentDTO studentDTO) throws SQLException {
		String query = " INSERT INTO students(name,age,email) VALUES(?,?,?) ";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, studentDTO.getName());
			pstmt.setInt(2, studentDTO.getAge());
			pstmt.setString(3, studentDTO.getEmail());
			pstmt.executeUpdate();
		}
	}

	// 학생 조회 기능 만들기 -- 아이디 기준
	public StudentDTO getStudentById(int id) throws SQLException {

		String query = " SELECT*FROM students WHERE id = ? ";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					return new StudentDTO(rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
							rs.getString("email"));
				}
			}
		}
		return null;
	}

	// 학생 전체 조회 기능
	public List<StudentDTO> getAllStudents() throws SQLException {
		// tip - 리스트라면 무조건 리스트를 생성하고 코드 작성1
		List<StudentDTO> list = new ArrayList<>();
		String query = " SELECT*FROM students ";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				StudentDTO dto = new StudentDTO().builder().id(rs.getInt("id")).name(rs.getString("name"))
						.age(rs.getInt("age")).email(rs.getString("email")).build();
				list.add(dto);
			}
		}
		return list;
	}

	// 학생 정보 수정하기
	public void  updateStudent(String name, StudentDTO studentDTO) throws SQLException {
		String query = " UPDATE student SET name = ?, age = ?, email =? where name =? ";
		try(Connection conn = DBConnectionManager.getInstance().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, studentDTO.getName());
			pstmt.setInt(2, studentDTO.getAge());
			pstmt.setString(3, studentDTO.getEmail());
			pstmt.setString(4, name);
			pstmt.executeUpdate();
		}
	}

	// 학생 정보 삭제하게
	public void deleteStudent(int id) throws SQLException {
		String query = " DELETE FROM students WHERE id =? ";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}
}

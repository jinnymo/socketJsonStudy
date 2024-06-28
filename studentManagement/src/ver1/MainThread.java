package ver1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainThread {
	
	// o
	private static final Logger LOGGER = Logger.getLogger(MainThread.class.getName());
	private static final String ADD_STUDENT = " insert into students(name,age,email) values (?,?,?) ";

	private static final String DELETE_STUDENT = " DELETE FROM students WHERE email = ? ;";

	private static final String SEARCH_STUDENT_ALL = " select*from students ";
	private static final String SEARCH_STUDENT_NAME = " select*from students where name = ? ";
	private static final String SEARCH_STUDENT_AGE = " select*from students where email = ? ";
	private static final String SEARCH_STUDENT_EMAIL = " select*from students where email = ? ";
	private static final String UPDATE_STUDENT_AGE = " update students set age = ? where name = ? ";
	private static final String UPDATE_STUDENT_EMAIL = " update students set email = ? where name = ? ";

	public static void main(String[] args) {

		try (Connection conn = DBConnectionManager.getConnection(); Scanner scanner = new Scanner(System.in)) {

			while (true) {
				printMenu();
				int choice = 0;
				try {
					choice = scanner.nextInt(); // 블로킹
				} catch (InputMismatchException e) {
					System.out.println("정수가 아님 다시 입력");
				}

				if (choice == 1) {
					addStudent(conn, scanner);
				} else if (choice == 2) {
					deleteStudent(conn, scanner);
					// viewQuizQuestion(conn);
				} else if (choice == 3 || choice == 4 || choice == 5) {
					viewStudentInfo(conn, scanner, choice);
					// playQuizGame(conn, scanner);
				} else if (choice == 6) {
					// updateage(conn, scanner, choice);
					updateage(conn,scanner);
					// playQuizGame(conn, scanner);
				} else if (choice == 7) {
					updateemail(conn, scanner);
					// updateage(conn, scanner, choice);

					// playQuizGame(conn, scanner);
				} else if (choice == 8) {
					System.out.println("프로그램을 종료 합니다");
					break;
				} else {
					System.out.println("잘못된 선택입니다. 다시 시도하세요.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void printMenu() {
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("1. 학생 추가");
		System.out.println("2. 학생 삭제 -- 이름 기준");
		System.out.println("3. 학생 조회 -- 전체");
		System.out.println("4. 학생 조회 -- 이름");
		System.out.println("5. 학생 조회 -- 이메일");
		System.out.println("6. 학생 나이 수정 -- 이름기준");
		System.out.println("7. 학생 이메일 수정 -- 이름기준");
		System.out.println("8. 종료");
		System.out.print("옵션을 선택 하세요: ");
	}

	private static void viewStudentInfo(Connection conn, Scanner sc, int num) {
		String sql = null;
		String data = null;
		if (num == 3) {
			sql = SEARCH_STUDENT_ALL;
			System.out.println("전체 학생을 조회 합니다.");
		} else if (num == 4) {
			sql = SEARCH_STUDENT_NAME;
			System.out.println("조회할 학생 이름을 입력 하세요");
			data = sc.next();
		} else if (num == 5) {
			sql = SEARCH_STUDENT_EMAIL;
			System.out.println("조회할 학생 이메일을 입력 하세요");
			data = sc.next();
		}
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			if (num == 3) {

			} else {
				pstmt.setString(1, data);

			}
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				System.out.println("학생이름 : " + resultSet.getString("name"));
				System.out.println("학생나이 : " + resultSet.getInt("age"));
				System.out.println("학생이메일 : " + resultSet.getString("email"));
				if (!resultSet.isLast()) {
					System.out.println("----------------------------------------");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void deleteStudent(Connection conn, Scanner scanner) {

		System.out.println("학생 이메일 입력 ");
		scanner.nextLine();
		String email = scanner.nextLine();

		try (PreparedStatement pstmt = conn.prepareStatement(DELETE_STUDENT)) {
			pstmt.setString(1, email);
			int rowsInsertedCount = pstmt.executeUpdate();
			checkexecute(rowsInsertedCount);
			System.out.println("추가된 행의 수 : " + rowsInsertedCount);
			if (rowsInsertedCount == 0) {
				System.out.println("존재 하지 않는 이메일");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void updateage(Connection conn, Scanner sc) {
		String sql = null;
		String data = null;
		int rowsInsertedCount = 0;
		while (rowsInsertedCount == 0) {
			System.out.println("수정할 학생의 이름을 입력하세요");
			String email = sc.next();
			System.out.println("나이 입력");
			int age = 0;
			try {
				age = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("정수만  입력 ㄱㄱ");
			}
			try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_STUDENT_AGE)) {

				pstmt.setInt(1, age);
				pstmt.setString(2, email);

				rowsInsertedCount = pstmt.executeUpdate();
				checkexecute(rowsInsertedCount);
				System.out.println("추가된 행의 수 : " + rowsInsertedCount);
				if (rowsInsertedCount == 0) {
					System.out.println("학생이름 오류 \\ 다시 입력");
				}
			} catch (SQLException e) {
				System.out.println("학생이름 오류 \\ 다시 입력");

			}
		}
		

	}
	private static void updateemail(Connection conn, Scanner sc) {
		String sql = null;
		String data = null;
		int rowsInsertedCount = 0;
		while (rowsInsertedCount == 0) {
			System.out.println("수정할 학생의 이름을 입력하세요");
			String name = sc.next();
			System.out.println("이메일 입력");
			String email = null;
			try {
				email = sc.next();
			} catch (InputMismatchException e) {
				System.out.println("문자열만 입력 ㄱㄱ");
			}
			try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_STUDENT_AGE)) {

				pstmt.setString(1, email);
				pstmt.setString(2, name);

				rowsInsertedCount = pstmt.executeUpdate();
				checkexecute(rowsInsertedCount);
				System.out.println("추가된 행의 수 : " + rowsInsertedCount);
				if (rowsInsertedCount == 0) {
					System.out.println("학생이름 오류 \\ 다시 입력");
				}
			} catch (SQLException e) {
				System.out.println("이미 있는 이메일 \\ 다시 입력");

			}
		}

	}

	private static void addStudent(Connection conn, Scanner scanner) {
		int rowsInsertedCount = 0;
		while(rowsInsertedCount == 0) {
			System.out.println("학생 이름 입력 ");
		scanner.nextLine();
		String name = scanner.nextLine();
		System.out.println("학생 나이 입력 ");
		String age = scanner.nextLine();
		System.out.println("학생 이메일 입력 ");
		String email = scanner.nextLine();

		try (PreparedStatement pstmt = conn.prepareStatement(ADD_STUDENT)) {
			pstmt.setString(1, name);
			pstmt.setString(2, age);
			pstmt.setString(3, email);
		rowsInsertedCount = pstmt.executeUpdate();
			checkexecute(rowsInsertedCount);
			System.out.println("추가된 행의 수 : " + rowsInsertedCount);
		} catch (SQLException e) {
			
			System.out.println("이메일 중복 || 나이 데이터타입 오류 다시 입력 ");
			
		}
		}
		
	}

	private static void checkexecute(int count) {
		if (count > 0) {
			System.out.println("작업 성공");
		} else {

		}
	}

}

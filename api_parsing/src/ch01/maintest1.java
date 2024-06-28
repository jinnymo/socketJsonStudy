package ch01;

import com.google.gson.Gson;

public class maintest1 {

	public static void main(String[] args) {
		
		//프로젝트 속성에서 빌드 패스 들어가서 클래스 패스에 add jars 등록
		Gson gson = new Gson();
		
		Student student1 = new Student();
		Student student2 = new Student("홍",20,"컴공");
		
	}
	public static void add() {
		
	}
}

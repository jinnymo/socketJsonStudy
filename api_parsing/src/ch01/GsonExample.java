package ch01;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonExample {

	
	public static void main(String[] args) {
		Student student = new Student("고길동",40,"애완학과");
	Student student2 = new Student("둘리",5,"문제학과");
	
	Student[] studentArr = {student,student2};
	
	// 특정 형식 있는 문자열로 변환
	//Gson gson = new Gson();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	//객체를 --> json 형식에 문자열로 변환 --> toJson() 호출''
	
	String student1Str = gson.toJson(student);
	System.out.println(student1Str);

	Gson gson2 = new Gson();
	String student2Str = gson2.toJson(student2);
	System.out.println(student2Str);
	
	//객체에서 --> 문자열 형태로 가능  반대로는?
	//문자열에서 --> 클래스 형태로
	
	Student studentObject = gson.fromJson(student1Str, Student.class);
	System.out.println(studentObject.getName());
	
	String sb = gson.toJson(studentArr);
	System.out.println(sb);
	Student[] sa = gson.fromJson(sb, Student[].class);
	System.out.println(sa[1].getAge());
	}
}

package ch03;

import ch02.Water;

public class MainTest3 {

	public static void main(String[] args) {
		//< T extends 클래스 > 사용하기
		//상위 클래스의 필요성
		// 위 문법을 사용해서 상위 클래스에 소한 자료형만 대체 문자 안에 들어올수 있다.
		GenericPrinter<Powder> genericPrinter1 = new GenericPrinter<>();
		genericPrinter1.setMaterial(new Powder());
		System.out.println(genericPrinter1.toString());
		
		GenericPrinter<Material> genericPrinter2 = new GenericPrinter<>();
		genericPrinter2.setMaterial(new Plastic());
		
	}
	
}

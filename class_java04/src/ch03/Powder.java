package ch03;

/*
 * T extends 클래스 문법을 사용하기 위해 설계
 */
public class Powder extends Material{

	@Override
	public void doPrinting() {
		// TODO Auto-generated method stub
		System.out.println("플라스틕 재료로 출력합니다.");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return"재료는 플라스틱입니다.";
	}
}

package ch03;

/*
 * T extends 클래스 문법을 사용하기 위해 설계
 */
public class Plastic extends Material{

	@Override
	public void doPrinting() {
		// TODO Auto-generated method stub
		System.out.println("파우더 재료로 출력합니다.");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return"재료는 파우더입니다.";
	}
}

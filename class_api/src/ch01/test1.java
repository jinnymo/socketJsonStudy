package ch01;

public class test1 {

	public static void main(String[] args) {
		//인스턴스 생성하지 않아도 호출가능
		Name.print();
		
		//인스턴스를 생성해야지만 호출가능
		Name name = new Name();
		name.print2();
		Math.max(0, 0);
	}

}

class Name{
	
	static void print() {
		System.out.println("홍길동");
	}
	void print2() {
		System.out.println("김철수");
	}
}
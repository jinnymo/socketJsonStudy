package ch02;

public class GenericPrinter<T> {

	// T라는 대체 묹자를 사용, E- element, K - key, V- value(이러한
	//약속이 있지만 사실은 아무 문자나 사용가능)
	//자료형 매개변수(type parameter)
	//이 클래스를 사용하는 시점에서 실제 사용된 자료형이 결ㅇ정된다.
	
	private T material; // T 대체 문자형으로 변수를 선언.
	
	public T getMaterial() {
		return material;
	}
	public void setMaterial(T material) {
		this.material = material;
	}
	
	@Override
	public String toString() {
		return material.toString();
	}
}

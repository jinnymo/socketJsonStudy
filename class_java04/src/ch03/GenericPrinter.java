package ch03;
/*
 * <T> Material 
 * material 를 상속받은 자식클래스만 대체 문자에 들어올수 있음...
 * <T extends Material>
 */
public class GenericPrinter<T extends Material> {

	private T material;
	
	public T getMaterial() {
		return material;
	}
	
	public void setMaterial(T material) {
		this.material = material;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return material.toString();
	}
}

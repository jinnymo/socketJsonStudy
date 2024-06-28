package ch01;

public class MainTest1 {

	public static void main(String[] args) {
		ThreeDPrinter dPrinter = new ThreeDPrinter();
		dPrinter.setMaterial(new Plastic());
		System.out.println(dPrinter.material.toString());
		
		
		ThreeDPrinter2 dPrinter2 = new ThreeDPrinter2();
		dPrinter2.setMaterial(new Powder());
		System.out.println(dPrinter2.material.toString());
		
		System.out.println("----------------------------");
		ThreeDPrinter3 dPrinter3 = new ThreeDPrinter3();
		dPrinter3.setMaterial(new Plastic());
		System.out.println(dPrinter3.material.toString());
		
		System.out.println("----------------2-------------");
		ThreeDPrinter3 dPrinter3_2 = new ThreeDPrinter3();
		dPrinter3_2.setMaterial(new Powder());
		System.out.println(dPrinter3_2.material.toString());
		
	}
	
}

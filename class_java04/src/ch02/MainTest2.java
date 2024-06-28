package ch02;

import ch01.Plastic;
import ch01.Powder;

public class MainTest2 {

	public static void main(String[] args) {
		
		Plastic plastic = new Plastic();
		Powder powder = new Powder();
		Water water = new Water();
		
		GenericPrinter<Object> genericPrinter1 = new GenericPrinter<>();
		genericPrinter1.setMaterial(plastic);
		//Plastic returnPlastic = genericPrinter1.getMaterial();
	
		System.out.println(genericPrinter1.toString());
		
		genericPrinter1.setMaterial(water);
		System.out.println(genericPrinter1.toString());
		
		
	}
	
}

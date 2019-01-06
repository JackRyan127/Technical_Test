package techtest;

public class Option implements Comparable<Option> {
	
	/*
	 * Option class used to get the Supplier ID, Car Type and Price from JSON format
	 */
	
	String supplier_id;
	String car_type;
	int price;
		
	public String getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getCar_type() {
		return car_type;
	}

	public int getPrice() {
		return price;
	}
	
	public int compareTo(Option toCompare) {
		return toCompare.price - this.price;
	}
 
}

package techtest;
public class Response {
	
	/*
	 * Class used to get the full response from JSON format
	 */
	
	String supplier_id;
	String pickup;
	String dropoff;
	Option[] options;
	
	public String getSupplier_id() {
		return supplier_id;
	}

	public String getPickup() {
		return pickup;
	}

	public String getDropoff() {
		return dropoff;
	}
	
	public Option[] getOptions() {
		return options;
	}
}

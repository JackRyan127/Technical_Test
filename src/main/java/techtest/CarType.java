package techtest;

public enum CarType {
	
	/*
	 * Gives the car_type and the number of seats it holds
	 */
	
	STANDARD (4),
	EXECUTIVE (4),
	LUXURY (4),
	PEOPLE_CARRIER (6),
	LUXURY_PEOPLE_CARRIER (6),
	MINIBUS (16);
	
	private final int numberOfSeats;
	
	CarType (int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	
	public int seats () { return numberOfSeats; } 
	
}

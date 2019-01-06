package techtest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Search {

	String pickup;
	String dropoff;
	int numberOfSeats;
	String[] suppliers;

	static final String HOST = "https://techtest.rideways.com/";
	static final String[] SUPPLIERS = {"dave", "eric", "jeff"};

	public Search (String[] args) throws Exception{
		this(args, "");
	}

	public Search (String[] args, String supplier) throws Exception{		
		pickup = args[0];
		dropoff = args[1];
		if (args.length == 3) {
			numberOfSeats = getSeats(args[2]);
		} else {
			numberOfSeats = 0;
		}
		if (!supplier.equals("")) {
			suppliers = supplier.split(",");
		} else {
			suppliers = SUPPLIERS;
		}
	}

	public int getSeats (String seats) {
		return Integer.parseInt(seats);
	}

	public ArrayList<Option> sendRequests () throws IOException {
		
		/*
		 * Sends requests for each supplier and returns the processed responses
		 */
		
		ArrayList<String> responses = new ArrayList<String>();
		for (String supplier : suppliers) {
			Request request = new Request(HOST, supplier, this.pickup, this.dropoff);
			responses.add(request.getResponse());
		}
		return processResponse(responses);
	}


	public ArrayList<Option> processResponse (ArrayList<String> responses) {
		
		/*
		 * Processes the responses from the requests. Returns the ordered list of the cheapest supplier of each car type.
		 */
		
		EnumMap<CarType, Option> eMap = new EnumMap<CarType, Option>(CarType.class);
		for (CarType car : CarType.values()) {
			eMap.put(car, null);
		}
		
		//for each car type, check if the price is lower than the current minimum
		
		for (String response : responses) {
			GsonBuilder gb = new GsonBuilder();
			Gson g = gb.create();
			Response r = g.fromJson(response, Response.class);
			if (r != null) {
				for (Option o : r.getOptions()) {
					Option lowest = eMap.get(CarType.valueOf(o.getCar_type()));
					
					//check if the current response price is lower than the current lowest
					
					if (lowest == null || o.getPrice() < lowest.getPrice()) {
						o.setSupplier_id(r.getSupplier_id());
						eMap.put(CarType.valueOf(o.getCar_type()), o);
					}
				}
			}
		}

		ArrayList<Option> result = new ArrayList<Option>();

		// for each car type, filter out the cars with less seats than required
		
		for (Object c : eMap.keySet().toArray()) {
			CarType ct = (CarType) c;
			if (ct.seats() >= this.numberOfSeats) {
				Option option = eMap.get(c);
				if (option != null)
					result.add(option);
			}
		}
		
		//return the sorted list in descending order of price

		Collections.sort(result);

		return result;

	}

}

package techtest;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import techtest.Option;
import techtest.Search;

@SpringBootApplication
public class App {  

	/*
	 * Main method of the technical test
	 */

	public static void main(String[] args) {	
	    
	    // Check to see if no arguments, or restapi given, start spring application if true
	    
		if (args.length == 0 || args[0].equals("--restapi") || args[0].equals("-r")) {
			SpringApplication.run(App.class, args);
		} else {
		    
		    // Otherwise check remain arguments to see if searching all suppliers or a given supplier
		    
			String[] new_args = null;
			String sup = "";
			if (args[0].equals("-s") || args[0].equals("--supplier")) {
			    
			    // Check to see the correct number of arguments are given
			    
				if (args.length == 4 || args.length == 5) {
					new_args = new String[args.length-2];
					sup = args[1];
					
					// Copy arguments and remove supplier
					
					System.arraycopy(args, 2, new_args, 0, args.length-2);
				} else {
					System.out.println("Incorrect number of parameters for -s(upplier), requires supplier, pickup, dropoff and seats (optional)");
					System.exit(0);
				}
				
			// Check if -a(ll) flag given
				
			} else if (args[0].equals("-a") || args[0].equals("--all")) {
                
                // Check to see the correct number of arguments are given
			    
				if (args.length == 3 || args.length == 4) {
					new_args = new String[args.length-1];
                    
                    // Copy arguments and remove "all" flag
					
					System.arraycopy(args, 1, new_args, 0, args.length-1);
				} else {
					System.out.println("Incorrect number of parameters for -a(ll), requires pickup, dropoff and seats (optional)");
					System.exit(0);
				}
			} else {
				System.out.println("Incorrect parameters, requires pickup, dropoff and seats (optional)");
				System.exit(0);
			}
			Search s = null;
			try {
			    
			    /*
			     *  Instantiate new search with given parameters and supplier
			     *  Supplier given as empty string for searching all suppliers
			     */
			    
				s = new Search(new_args, sup);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (s != null) {
				try {
					ArrayList<Option> result = s.sendRequests();
					for (Option option : result) {
						if (sup.equals("") || sup.split("").length > 1) {
						    
						    // If searching more than one supplier, print out supplier name, otherwise do not
						    
							System.out.println(option.getCar_type() + " - " + option.getSupplier_id() + " - " + option.getPrice());							
						} else {
							System.out.println(option.getCar_type() + " - " + option.getPrice());
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}	
}
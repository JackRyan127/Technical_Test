package techtest;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	/*
	 * Class used to respond to requests
	 */

    @RequestMapping("/taxi")
    public ArrayList<Option> result(@RequestParam(value="pickup") String pickup, @RequestParam(value="dropoff") String dropoff, @RequestParam(value="seats", defaultValue="0") String seats) {
    	
    	/*
    	 * Method returns the result from a new search using the given parameters
    	 */
    	
    	String[] args = {pickup, dropoff, seats};
    	Search s;
		try {
			s = new Search(args);
			return s.sendRequests();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
}
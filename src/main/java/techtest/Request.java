package techtest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class Request {
	
	private String host;
	private String request_url;
	private String supplier;
	private HttpURLConnection con;
	
	public Request (String host, String supplier, String pickup, String dropoff) {
		this.host = host;
		request_url = generateURL(supplier, pickup, dropoff);
		this.supplier = supplier;
	}
	
	public String generateURL (String supplier, String pickup, String dropoff) {	
		
		/*
		 * Generate the URL to send the request too
		 */
		
		String params = constructParams (pickup, dropoff);
		String url = host + supplier + "/" + params;
		return url;
	}
		
	public String constructParams (String pickup, String dropoff) {
		
		/*
		 * Construct the parameters of the request from the pickup and dropoff location
		 */
		
		String params = "?pickup=" + pickup + "&" + 
				"dropoff=" + dropoff;
		return params;
	}
	
	public void sendGetRequest () throws IOException, SocketTimeoutException {
		
		/*
		 * Send the get request with the given parameters, returns the opened connection
		 */
		
		URL url = new URL(request_url);
		HttpURLConnection con;
		con = (HttpURLConnection) url.openConnection();
		
		//set the connection timeout to 2s
		
		con.setConnectTimeout(2000);
		con.setRequestMethod("GET");
		this.con = con;
	}
	
	public String getResponse () throws IOException {
		
		/*
		 * Gets the response from the opened connection, returns the JSON as a String
		 */
		
		sendGetRequest();
		int responseCode = con.getResponseCode();
		
		//checks the response code == 200
		
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			return response.toString();
		} else {
			handleError(con);
			return null;
		}
	}
	
	public void handleError (HttpURLConnection con) throws IOException{
		int responseCode = con.getResponseCode();
		switch (responseCode) {
			case HttpURLConnection.HTTP_BAD_REQUEST: 
				System.out.println("GET request failed from " + supplier + ": Bad Request");
				break;
			case HttpURLConnection.HTTP_INTERNAL_ERROR:
				System.out.println("GET request failed from " + supplier + ": Internal Server Error");
				break;
			case HttpURLConnection.HTTP_UNAVAILABLE:
				System.out.println("GET request failed from " + supplier + ": Service Unavailable");
				break;
			default:
				System.out.println("GET request failed from " + supplier);
				break;
				
		}
	}
	
	public void closeConnection () {
		con.disconnect();
	}
	
	
	
}

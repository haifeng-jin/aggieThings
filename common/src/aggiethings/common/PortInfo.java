package aggiethings.common;

/**
 * PortInfo is a class stores all the information needed for socket connection
 * like the IP addresses and port.
 * @author Haifeng Jin
 */

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class PortInfo {

	public final static String baseURI = "http://localhost:8080/";
	public final static String cloudBaseURI = "http://localhost:8081/";
	public final static String[] aggregatorBaseURI = { "http://localhost:8082/", "http://localhost:8083/" };
	public static String aggregatorPath = "aggregator";
	public static String cloudPath = "cloud";
	//TODO: change the name of target
	private static WebTarget target = ClientBuilder.newClient().target(baseURI).path("config/address");
	public final static String notAvailable = "Not Available";

	/**
	 * It would block the thread until it successfully got the address.
	 */
	public static String getAggregatorAddress(int id) {
		String address = new String(notAvailable);
		while (address.equals(notAvailable)) {
			address = target.path(aggregatorPath).path(Integer.toString(id)).request(MediaType.TEXT_PLAIN).get(String.class);
		}
		return address;
	}

	/**
	 * It would block the thread until it successfully got the address.
	 */
	public static String getCloudAddress() {
		String address = new String(notAvailable);
		while (address.equals(notAvailable)) {
			address = target.path(cloudPath).request(MediaType.TEXT_PLAIN).get(String.class);
		}
		return address;
	}
}

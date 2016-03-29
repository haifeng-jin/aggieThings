package common;

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
	public final static String aggregatorBaseURI = "http://localhost:8082/";
	public static String aggregatorPath = "aggregator";
	public static String cloudPath = "cloud";
	private static WebTarget target = ClientBuilder.newClient().target(baseURI).path("config/address");


	public static String getAggregatorAddress(int id) {
		return target.path(aggregatorPath).path(Integer.toString(id)).request(MediaType.TEXT_PLAIN).get(String.class);
	}
	public static String getCloudAddress() {
		String ret = null;
		try {
			ret = target.path(cloudPath).request(MediaType.TEXT_PLAIN).get(String.class);
		} catch (Exception e) {
			System.out.println("Error occured during getting cloud address from config server.");
		}
		return ret;
	}
}

package common;

/*
 * PortInfo is a class stores all the information needed for socket connection
 * like the IP addresses and port.
 */

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


public class PortInfo {
	
	public static String baseURI = "http://localhost:8080/";
	public static String aggregatorPath = "aggregator";
	public static String cloudPath = "cloud";
	private static WebTarget target = ClientBuilder.newClient().target(baseURI).path("config/address");


	public static String getAggregatorAddress() {
		return target.path("aggregator").request(MediaType.TEXT_PLAIN).get(String.class);
	}
	public static String getCloudAddress() {
		return target.path("cloud").request(MediaType.TEXT_PLAIN).get(String.class);
	}
}

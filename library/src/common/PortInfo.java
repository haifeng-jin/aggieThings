package common;

/*
 * PortInfo is a class stores all the information needed for socket connection
 * like the IP addresses and port.
 */

public class PortInfo {
	
	public static String baseURI = "http://localhost:8080/";
	public static String aggregatorPath = "aggregator";
	public static String serverPath = "server";


	public static String getAggregatorAddress() {
		return baseURI + aggregatorPath;
	}
	public static String getServerAddress() {
		return baseURI + serverPath;
	}
}

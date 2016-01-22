package common;

/*
 * PortInfo is a class stores all the information needed for socket connection
 * like the IP addresses and port.
 */

public class PortInfo {

	public static String getAggregatorAddress() {
		return "http://localhost:8080/aggregator";
	}
	public static String getServerAddress() {
		return "http://localhost:8080/server";
	}
}

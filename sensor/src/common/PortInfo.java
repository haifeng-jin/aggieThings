package common;

/*
 * PortInfo is a class stores all the information needed for socket connection
 * like the IP addresses and port.
 */

public class PortInfo {

	private static int aggregatorPort = 3456;
	private static int serverPort = 3457;

	public static int getServerPort() {
		return serverPort;
	}
	public static int getAggregatorPort() {
		return aggregatorPort;
	}

	private PortInfo() {
	}

	public static String getAggregatorAddress() {
		return "http://localhost:8080/";
	}
}

package aggie.things.common;

/*
 * PortInfo is a class stores all the information needed for socket connection
 * like the IP addresses and port.
 */

public class PortInfo {
	private static String address = "127.0.0.1";

	private static int aggregatorPort = 3456;
	private static int serverPort = 3457;

	public static int getServerPort() {
		return serverPort;
	}
	public static int getAggregatorPort() {
		return aggregatorPort;
	}

	public static String getAddress() {
		return new String(address);
	}

	private PortInfo() {
	}
}

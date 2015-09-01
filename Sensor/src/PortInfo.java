/*
 * Some information about the socket connections including the address and the port.
 * This class uses singleton design pattern.
 */

public class PortInfo {
	private static String address = "127.0.0.1";

	private static int port = 8080;

	public static int getPort() {
		return port;
	}

	public static String getAddress() {
		return new String(address);
	}

	private PortInfo() {
	}
}

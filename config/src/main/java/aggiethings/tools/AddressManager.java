package aggiethings.tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import common.FileLineGetter;

/**
 * Manage the files storing the addresses of the cloud server the aggregators.
 * @author Haifeng Jin
 *
 */
public class AddressManager {

	static String baseAddress = "src/main/resources/";

	public static String getAggregatorAddress() {
		return getContent("aggregatorAddress");
	}

	public static void setAggregatorAddress(String tempAddress) {
		setContent("aggregatorAddress", tempAddress);
	}

	public static void setCloudAddress(String tempAddress) {
		setContent("cloudAddress", tempAddress);
		
	}

	public static String getCloudAddress() {
		return getContent("cloudAddress");
	}	

	private static void setContent(String path, String tempAddress) {
		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream(baseAddress + path));
			writer.println(tempAddress);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	private static String getContent(String path) {
		return new FileLineGetter(baseAddress + path).nextLine();
	}


}

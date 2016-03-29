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

	String baseAddress;

	public AddressManager(String addressfolderpath) {
		baseAddress = addressfolderpath;
	}

	public String getAggregatorAddress() {
		return getContent("aggregatorAddress");
	}

	public void setAggregatorAddress(String tempAddress) {
		setContent("aggregatorAddress", tempAddress);
	}

	public void setCloudAddress(String tempAddress) {
		setContent("cloudAddress", tempAddress);
		
	}

	public String getCloudAddress() {
		return getContent("cloudAddress");
	}	

	private void setContent(String path, String tempAddress) {
		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream(baseAddress + path));
			writer.println(tempAddress);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	private String getContent(String path) {
		return new FileLineGetter(baseAddress + path).nextLine();
	}


}

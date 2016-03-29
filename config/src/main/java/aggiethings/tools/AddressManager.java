package aggiethings.tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import common.FileLineGetter;

/**
 * Manage the files storing the addresses of the cloud server the aggregators.
 * @author Haifeng Jin
 *
 */
public class AddressManager {

	String baseAddress;
	private String cloudAddress = "";
	private HashMap<Integer, String> aggregatorAddress = new HashMap<Integer, String>();

	public AddressManager(String addressfolderpath) {
		baseAddress = addressfolderpath;
	}

	public String getAggregatorAddress(int id) {
		return aggregatorAddress.get(id);
		//return getContent("aggregatorAddress");
	}

	public void setAggregatorAddress(int id, String tempAddress) {
		aggregatorAddress.put(id, tempAddress);
		//setContent("aggregatorAddress", tempAddress);
	}

	public void setCloudAddress(String tempAddress) {
		cloudAddress = tempAddress;
		//setContent("cloudAddress", tempAddress);
	}

	public String getCloudAddress() {
		return cloudAddress;
		//return getContent("cloudAddress");
	}	

	void setContent(String path, String tempAddress) {
		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream(baseAddress + path));
			writer.println(tempAddress);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	String getContent(String path) {
		return new FileLineGetter(baseAddress + path).nextLine();
	}


}

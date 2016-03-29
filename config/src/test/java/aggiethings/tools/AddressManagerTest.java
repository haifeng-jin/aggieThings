package aggiethings.tools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AddressManagerTest {

	public final static String addressFolderPath = "src/test/resources/";
	private String tempAddress = "http://localhost:8080/test";
	private AddressManager manager;
	
	@Before
	public void setUp() {
		manager = new AddressManager(addressFolderPath);
	}

	@Test
	public void testAggregatorAddress() {
		manager.setAggregatorAddress(tempAddress);
		String response = manager.getAggregatorAddress();
		assertEquals(tempAddress, response);
	}

	@Test
	public void testCloudAddress() {
		manager.setCloudAddress(tempAddress);
		String response = manager.getCloudAddress();
		assertEquals(tempAddress, response);
	}
}

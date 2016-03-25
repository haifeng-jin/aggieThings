package aggiethings.tools;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddressManagerTest {

	private String tempAddress = "http://localhost:8080/test";
	
	@Before
	public void setUp() {
		AddressManager.baseAddress = "src/test/resources/";
	}

	@Test
	public void testAggregatorAddress() {
		AddressManager.setAggregatorAddress(tempAddress);
		String response = AddressManager.getAggregatorAddress();
		assertEquals(tempAddress, response);
	}

	@Test
	public void testCloudAddress() {
		AddressManager.setCloudAddress(tempAddress);
		String response = AddressManager.getCloudAddress();
		assertEquals(tempAddress, response);
	}
	
	@After
	public void setBack() {
		AddressManager.baseAddress = "src/main/resources/";
	}
}

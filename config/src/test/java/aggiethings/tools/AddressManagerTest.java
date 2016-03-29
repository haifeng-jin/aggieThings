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
		for (int i = 0; i < 10; i++) {
			manager.setAggregatorAddress(i, tempAddress);
			String response = manager.getAggregatorAddress(i);
			assertEquals(tempAddress, response);
		}
	}

	@Test
	public void testAggregatorAddress2() {
			manager.setAggregatorAddress(0, "a");
			manager.setAggregatorAddress(1, "b");

			String response;

			response = manager.getAggregatorAddress(0);
			assertEquals("a", response);

			response = manager.getAggregatorAddress(1);
			assertEquals("b", response);

			manager.setAggregatorAddress(0, "c");

			response = manager.getAggregatorAddress(0);
			assertEquals("c", response);
	}
	@Test
	public void testCloudAddress() {
		manager.setCloudAddress(tempAddress);
		String response = manager.getCloudAddress();
		assertEquals(tempAddress, response);
	}
}

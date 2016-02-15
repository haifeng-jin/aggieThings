package aggiethings.webresource;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.DataItem;
import common.PortInfo;

public class AggregatorResourceTest {

	private static WebTarget target;

	@Before
	public void setUp() throws Exception {
		Main.start();
		// create the client
		Client c = ClientBuilder.newClient();

		// uncomment the following line if you want to enable
		// support for JSON in the client (you also have to uncomment
		// dependency on jersey-media-json module in pom.xml and
		// Main.startServer())
		// --
		// c.configuration().enable(new
		// org.glassfish.jersey.media.json.JsonJaxbFeature());

		target = c.target(Main.BASE_URI).path(PortInfo.aggregatorPath);
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 * @throws InterruptedException 
	 */
	@Test
	public void testGetIt() {
		String responseMsg = target.request().get(String.class);
		assertEquals("Got it!", responseMsg);
	}

	public void testEcho() {
		DataItem item = new DataItem(new byte[3]);
		DataItem response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(item.toString(), MediaType.TEXT_PLAIN), DataItem.class);
		assertEquals(item, response);

	}

	public void testReceive() throws InterruptedException {
		DataItem item = new DataItem(new byte[1]);
		DataItem response = postDataItem(item);
		assertEquals(item.getData()[0], response.getData()[0]);
		Thread.sleep(5000);
	}

	public void testCost() throws InterruptedException {
		
		postDataItem(new DataItem(new byte[20]));
		postDataItem(new DataItem(new byte[40]));
		postDataItem(new DataItem(new byte[60]));
		Thread.sleep(500);
		int traffic = Integer.parseInt(target.path("cost").path("traffic").request().get(String.class));
		int storage = Integer.parseInt(target.path("cost").path("storage").request().get(String.class));
		assertEquals(20 + 40 + 60, traffic);
		assertTrue(storage <= 20 + 40 + 60);
	}
	
	private DataItem postDataItem(DataItem item) {
		
		return target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(item, MediaType.APPLICATION_JSON), DataItem.class);
	}

	@After
	public void stop() {
		Main.stop();
	}
}

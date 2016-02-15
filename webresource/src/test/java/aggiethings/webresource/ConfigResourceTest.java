package aggiethings.webresource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import aggiethings.tools.AddressManagerTest;

import static org.junit.Assert.assertEquals;

public class ConfigResourceTest {

	private WebTarget target;
	private Object tempAddress = "http://localhost:8080/testAddress";
	private AddressManagerTest temp = new AddressManagerTest();
	private Main m;

	@Before
	public void setUp() throws Exception {
		m = new Main();
		m.start();
		// create the client
		Client c = ClientBuilder.newClient();

		// uncomment the following line if you want to enable
		// support for JSON in the client (you also have to uncomment
		// dependency on jersey-media-json module in pom.xml and
		// Main.startServer())
		// --
		// c.configuration().enable(new
		// org.glassfish.jersey.media.json.JsonJaxbFeature());
		temp.setUp();
		target = c.target(Main.BASE_URI).path("config/address");
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testSetAggregatorAddress() {
		String responseMsg = target.path("aggregator").request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(tempAddress, MediaType.TEXT_PLAIN), String.class);
		assertEquals(tempAddress, responseMsg);
	}

	@Test
	public void testGetAggregatorAddress() {
		target.path("aggregator").request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(tempAddress, MediaType.TEXT_PLAIN), String.class);
		String responseMsg = target.path("aggregator").request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(tempAddress, responseMsg);
	}

	@Test
	public void testSetCloudAddress() {
		String responseMsg = target.path("cloud").request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(tempAddress, MediaType.TEXT_PLAIN), String.class);
		assertEquals(tempAddress, responseMsg);
	}

	@Test
	public void testGetCloudAddress() {
		target.path("cloud").request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(tempAddress, MediaType.TEXT_PLAIN), String.class);
		String responseMsg = target.path("cloud").request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(tempAddress, responseMsg);
	}

	@After
	public void stop() {
		m.stop();
		temp.setBack();
	}
}

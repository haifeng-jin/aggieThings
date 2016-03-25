package aggiethings.config;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import aggiethings.config.ConfigResource;
import aggiethings.config.Main;
import aggiethings.tools.AddressManagerTest;
import aggiethings.tools.TimeManager;
import common.FileGetter;

import static org.junit.Assert.assertEquals;

public class ConfigResourceTest {

	private WebTarget target;
	private Object tempAddress = "http://localhost:8080/testAddress";
	private AddressManagerTest temp = new AddressManagerTest();

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
		temp.setUp();
		target = c.target(Main.BASE_URI).path("config");
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testSetAggregatorAddress() {
		String responseMsg = target.path("address").path("aggregator").request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(tempAddress, MediaType.TEXT_PLAIN), String.class);
		assertEquals(tempAddress, responseMsg);
	}

	@Test
	public void testGetAggregatorAddress() {
		target.path("address").path("aggregator").request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(tempAddress, MediaType.TEXT_PLAIN), String.class);
		String responseMsg = target.path("address").path("aggregator").request(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println(responseMsg);
		assertEquals(tempAddress, responseMsg);
	}

	@Test
	public void testSetCloudAddress() {
		String responseMsg = target.path("address").path("cloud").request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(tempAddress, MediaType.TEXT_PLAIN), String.class);
		assertEquals(tempAddress, responseMsg);
	}

	@Test
	public void testGetCloudAddress() {
		target.path("address").path("cloud").request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(tempAddress, MediaType.TEXT_PLAIN), String.class);
		String responseMsg = target.path("address").path("cloud").request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(tempAddress, responseMsg);
	}

	@Test
	public void testGetCloudTime() {
		String responseMsg = target.path("time").path("cloud").request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(Integer.toString(TimeManager.getCloudDiff()), responseMsg);
	}

	@Test
	public void testGetAggregatorTime() {
		String responseMsg = target.path("time").path("aggregator").request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(Integer.toString(TimeManager.getAggregatorDiff()), responseMsg);
	}
	
	@Test
	public void testGetConfigFile() {
		String responseMsg = target.path("file").request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(FileGetter.getContent(ConfigResource.configFilePath), responseMsg);
	}

	@After
	public void stop() {
		Main.stop();
		temp.setBack();
	}
}

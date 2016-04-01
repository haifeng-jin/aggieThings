package aggiethings.config;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.FileGetter;
import common.PortInfo;
import aggiethings.tools.AddressManager;
import aggiethings.tools.AddressManagerTest;
import aggiethings.tools.TimeManager;

public class ConfigResourceTest {

	private WebTarget target;
	private String tempAddress = "http://localhost:8080/testAddress";

	@Before
	public void setUp() throws Exception {
		Main.start();
		Main.addressManager = new AddressManager(AddressManagerTest.addressFolderPath);
		Main.timeManager = mock(TimeManager.class);

		// create the client
		Client c = ClientBuilder.newClient();

		// uncomment the following line if you want to enable
		// support for JSON in the client (you also have to uncomment
		// dependency on jersey-media-json module in pom.xml and
		// Main.startServer())
		// --
		// c.configuration().enable(new
		// org.glassfish.jersey.media.json.JsonJaxbFeature());
		target = c.target(Main.BASE_URI).path("config");
	}

	@Test
	public void testGotIt() {
		String responseMsg = target.request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals("Got it!", responseMsg);
	}

	@Test
	public void testSetAggregatorAddressEcho() {
		for (int i = 0; i < 10; i++) {
			String responseMsg = target.path("address").path("aggregator").path(Integer.toString(i))
					.request(MediaType.TEXT_PLAIN).post(Entity.entity(tempAddress, MediaType.TEXT_PLAIN), String.class);
			assertEquals(tempAddress, responseMsg);
		}
	}

	@Test
	public void testGetAggregatorAddress() {
		for (int i = 0; i < 10; i++) {
			target.path("address").path("aggregator").path(Integer.toString(i)).request(MediaType.TEXT_PLAIN)
					.post(Entity.entity(tempAddress, MediaType.TEXT_PLAIN), String.class);
			String responseMsg = target.path("address").path("aggregator").path(Integer.toString(i))
					.request(MediaType.TEXT_PLAIN).get(String.class);
			assertEquals(tempAddress, responseMsg);
		}
	}

	@Test
	public void testGetAggregatorAddressNotAvailable() {
		String responseMsg = target.path("address").path("aggregator").path(Integer.toString(0))
				.request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(PortInfo.notAvailable, responseMsg);
	}

	@Test
	public void testSetCloudAddressEcho() {
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
	public void testGetCloudAddressNotAvailable() {
		String responseMsg = target.path("address").path("cloud").request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(PortInfo.notAvailable, responseMsg);
	}

	@Test
	public void testGetCloudTime() {
		final int diff = 10;
		when(Main.timeManager.getCloudDiff()).thenReturn(diff);
		String responseMsg = target.path("time").path("cloud").request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(Integer.toString(diff), responseMsg);
	}

	@Test
	public void testGetAggregatorTime() {
		for (int i = 0; i < 10; i++) {
			when(Main.timeManager.getAggregatorDiff(i)).thenReturn(i);
		}

		for (int i = 0; i < 10; i++) {
			String responseMsg = target.path("time").path("aggregator").path(Integer.toString(i))
					.request(MediaType.TEXT_PLAIN).get(String.class);
			assertEquals(Integer.toString(i), responseMsg);
		}
	}

	@Test
	public void testGetConfigFile() {
		String responseMsg = target.path("file").request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(FileGetter.getContent(ConfigResource.configFilePath), responseMsg);
	}

	@Test
	public void testGetAggregatorId() {
		for (int i = 0; i < 10; i++) {
			String responseMsg = target.path("aggregator").path("id").request(MediaType.TEXT_PLAIN).get(String.class);
			int id = Integer.valueOf(responseMsg);
			assertEquals(i, id);
		}
	}

	@After
	public void stop() {
		Main.stop();
	}
}

package aggiethings.aggregator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import aggiethings.upload.UploadBuffer;
import aggiethings.upload.Uploader;
import common.DataItem;
import common.PortInfo;

public class AggregatorResourceTest {

	private static WebTarget target;
	private final Integer maxStorage = 15;
	private final Integer traffic = 20;

	@Before
	public void setUp() throws Exception {
		Main.BASE_URI = PortInfo.aggregatorBaseURI[0];
		Main.start();
		UploadBuffer buffer = mock(UploadBuffer.class);
		Uploader uploader = mock(Uploader.class);
		when(buffer.getMaxStorage()).thenReturn(maxStorage);
		when(buffer.getTraffic()).thenReturn(traffic);
		Main.buffer = buffer;
		Main.uploader = uploader;
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

	@Test
	public void testEcho() {
		DataItem item = new DataItem(new byte[3]);
		DataItem response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(item.toString(), MediaType.TEXT_PLAIN), DataItem.class);
		assertTrue(Arrays.equals(item.getData(), response.getData()));
		assertEquals(item.getTimestamp(0), response.getTimestamp(0));
	}

	@Test
	public void testReceive() throws InterruptedException {
		DataItem item = new DataItem(new byte[1]);
		DataItem response = postDataItem(item);
		assertEquals(item.getData()[0], response.getData()[0]);
	}

	@Test
	public void testCost() throws InterruptedException {
		
		Integer responseTraffic = Integer.parseInt(target.path("cost").path("traffic").request().get(String.class));
		Integer responseMaxStorage = Integer.parseInt(target.path("cost").path("storage").request().get(String.class));
		assertEquals(traffic, responseTraffic);
		assertEquals(maxStorage, responseMaxStorage);
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

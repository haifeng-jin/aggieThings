package aggiethings.webresource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import aggiethings.cloud.CloudServer;
import common.DataItem;
import common.PortInfo;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;


public class CloudResourceTest {

	private WebTarget target;

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

		target = c.target(Main.BASE_URI).path(PortInfo.cloudPath);
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testGetIt() {
		String responseMsg = target.request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals("Got it!", responseMsg);
	}

	@Test
	public void testEcho() {
		String item = new String("test");
		String response = target.request(MediaType.TEXT_PLAIN).post(Entity.entity(item, MediaType.TEXT_PLAIN),
				String.class);
		assertEquals(item + " received!", response);

	}

	@Test
	public void testEcho2() {
		DataItem item = new DataItem(new byte[1]);
		DataItem response = postDataItem(item);
		assertEquals(item.getData()[0], response.getData()[0]);
	}

	@Test
	public void testQuery() {
		CloudServer cloud = Main.cloud;
		DataItem item = new DataItem(new byte[1]);
		item.setTimestamp(new Timestamp(1));
		cloud.insert(item);
		DataItem response = target.request(MediaType.APPLICATION_JSON).get(DataItem.class);
		assertEquals(cloud.query(), response);
	}

	@Test
	public void testCost() throws InterruptedException {
		postDataItem(new DataItem(new byte[20]));
		postDataItem(new DataItem(new byte[20]));
		postDataItem(new DataItem(new byte[20]));
		postDataItem(new DataItem(new byte[20]));
		Thread.sleep(500);
		int cost = Integer.parseInt(target.path("cost").path("storage").request().get(String.class));
		assertEquals(20 * 4, cost);
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

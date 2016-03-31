package aggiethings.aggregator;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import aggiethings.upload.UploadBuffer;
import aggiethings.upload.Uploader;
import common.PortInfo;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Main class.
 *
 */
public class Main {
	// Base URI the Grizzly HTTP server will listen on
	public static String BASE_URI;
	static Uploader uploader;
	static UploadBuffer buffer;
	private static HttpServer server;
	static String cloudAddress;
	static int id;

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */

	public static void start() {
		// create a resource config that scans for JAX-RS resources and
		// providers
		// in aggiethings.aggregator package
		final ResourceConfig rc = new ResourceConfig().packages("aggiethings.aggregator");

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
	}

	private static void startUploader() {
		buffer = new UploadBuffer(1000);
		uploader = new Uploader(buffer, PortInfo.getCloudAddress());
	}

	private static String getCurrentAddress() {
		return BASE_URI + "aggregator";
	}

	private static void postAddressToConfig(String address) {
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target(PortInfo.baseURI).path("config");
		target.path("address").path("aggregator").path(Integer.toString(id)).request(MediaType.TEXT_PLAIN)
						.post(Entity.entity(address, MediaType.TEXT_PLAIN), String.class);
	}

	public static void stop() {
		server.shutdown();
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		id = Integer.valueOf(args[0]);
		BASE_URI = PortInfo.aggregatorBaseURI[id];
		start();
		postAddressToConfig(getCurrentAddress());
		startUploader();
		new Thread(uploader).start();
		System.out.println(String.format(
				"Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...",
				BASE_URI));
		System.in.read();
		uploader.stop();
		stop();
	}
}

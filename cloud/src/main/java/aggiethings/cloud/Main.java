package aggiethings.cloud;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import aggiethings.cloud.CloudServer;
import aggiethings.common.PingHttp;
import aggiethings.common.PortInfo;

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
	public static final String BASE_URI = PortInfo.cloudBaseURI;
	static CloudServer cloud;
	private static HttpServer server;

	public static void start() {
		// create a resource config that scans for JAX-RS resources and
		// providers
		// in aggiethings.aggregator package
		final ResourceConfig rc = new ResourceConfig().packages("aggiethings.cloud");

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
		startCloud();
	}

	private static void startCloud() {
		cloud = new CloudServer();
	}

	private static String getCurrentAddress() {
		return BASE_URI + "cloud";
	}

	private static void postAddressToConfig(String address) {
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target(PortInfo.baseURI).path("config");
		target.path("address").path("cloud").request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(address, MediaType.TEXT_PLAIN), String.class);
	}

	public static void stop() {
		server.shutdown();
	}

	public static void main(String[] args) throws IOException {
		start();

		final String configURL = PortInfo.baseURI + "config";
		PingHttp.wait(configURL);

		postAddressToConfig(getCurrentAddress());

		System.out.println(String.format(
				"Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...",
				BASE_URI));
		System.in.read();
		stop();
	}
}

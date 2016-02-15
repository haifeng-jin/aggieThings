package aggiethings.webresource;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import aggiethings.cloud.CloudServer;
import common.PortInfo;
import upload.UploadBuffer;
import upload.Uploader;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
	// Base URI the Grizzly HTTP server will listen on
	public static final String BASE_URI = PortInfo.baseURI;
	private static Uploader uploader;
	public static UploadBuffer buffer;
	static CloudServer cloud;
	private static HttpServer server;

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */
	public static void start() {
		server = startServer();
		startUploader();
		startCloud();
	}

	public static HttpServer startServer() {
		// create a resource config that scans for JAX-RS resources and
		// providers
		// in aggiethings.aggregator package
		final ResourceConfig rc = new ResourceConfig().packages("aggiethings.webresource");

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
	}

	private static void startCloud() {
		cloud = new CloudServer();
	}

	private static void startUploader() {
		buffer = new UploadBuffer(1000);
		uploader = new Uploader(buffer);
		new Thread(uploader).start();
	}

	public static void stop() {
		uploader.stop();
		server.shutdown();
	}
	/**
	 * Main method.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		start();
		System.out.println(String.format(
				"Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...",
				BASE_URI));
		System.in.read();
		stop();
	}
}

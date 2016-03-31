package aggiethings.upload;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import common.DataItem;

/*
 * The uploader is a single thread running in parallel with Aggregator.
 * It uploads what in the buffer as soon as possible.
 */

public class Uploader implements Runnable {

	UploadBuffer uploadBuffer;
	private WebTarget target;
	private boolean running;

	Uploader() {

	}

	public Uploader(UploadBuffer uploadBuffer, String address) {
		this.uploadBuffer = uploadBuffer;

		Client c = ClientBuilder.newClient();
		try {
		target = c.target(address);
		} catch (NullPointerException e) {
			System.out.println("The address String is null. Set it to empty string.");
			target = c.target("");
		}
	}

	public void run() {
		// Upload one DataItem at a time.
		running = true;
		while (running) {
			DataItem item = uploadBuffer.take();
			if (item != null)
				upload(item);
		}
	}

	public void stop() {
		running = false;
	}

	private void upload(DataItem item) {
		DataItem response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(item, MediaType.APPLICATION_JSON), DataItem.class);
		System.out.println("Aggregator: " + response);
	}

}

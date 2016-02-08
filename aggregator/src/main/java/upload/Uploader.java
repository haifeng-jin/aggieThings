package upload;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import common.DataItem;
import common.PortInfo;

/*
 * The uploader is a single thread running in parallel with Aggregator.
 * It uploads what in the buffer as soon as possible.
 */

public class Uploader implements Runnable {

	UploadBuffer uploadBuffer;
	private WebTarget target;

	Uploader() {

	}

	public Uploader(UploadBuffer uploadBuffer) {
		this.uploadBuffer = uploadBuffer;

		Client c = ClientBuilder.newClient();
		target = c.target(PortInfo.getServerAddress());
	}

	public void run() {
		// Upload one DataItem at a time.
		while (true) {
			DataItem item = uploadBuffer.take();
			if (item != null)
				upload(item);
		}
	}

	private void upload(DataItem item) {
		// TODO Auto-generated method stub
		DataItem response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(item, MediaType.APPLICATION_JSON), DataItem.class);
		System.out.println("Aggregator: " + response);
	}

}

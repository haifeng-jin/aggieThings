package aggregator;

import common.DataItem;

/*
 * The uploader is a single thread running in parallel with Aggregator.
 * It uploads what in the buffer as soon as possible.
 */

public class Uploader implements Runnable {

	UploadBuffer uploadBuffer;

	Uploader() {

	}

	public Uploader(UploadBuffer uploadBuffer) {
		this.uploadBuffer = uploadBuffer;
	}

	public void run() {
		// Upload one DataItem at a time.
		while (true) {
			DataItem item = uploadBuffer.take();
			upload(item);
		}
	}

	private void upload(DataItem item) {
		// TODO Auto-generated method stub

	}

}

package aggregator;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import common.DataItem;
import common.PortInfo;

public class Uploader implements Runnable {

	BlockingQueue<DataItem> uploadBuffer;

	public Uploader(BlockingQueue<DataItem> uploadBuffer) {
		this.uploadBuffer = uploadBuffer;
	}

	public void run() {
		while (true) {
			try {
				DataItem item = uploadBuffer.take();
				Socket socketClient = new Socket(PortInfo.getAddress(),
						PortInfo.getServerPort());
				ObjectOutputStream outputStream = new ObjectOutputStream(
						socketClient.getOutputStream());

				outputStream.writeObject(item);
				// null indicating the end of the data
				outputStream.writeObject(null);

				outputStream.close();
				socketClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
package aggie.things.handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.concurrent.BlockingQueue;

import aggie.things.common.DataItem;

/*
 * It handles all the data from one sensor.
 * It runs as a separate thread.
 */
public class SensorHandler extends Handler {
	
	BlockingQueue<DataItem> uploadBuffer;

	public SensorHandler(Socket client, int id, BlockingQueue<DataItem> uploadBuffer) {
		super(client, id);
		this.uploadBuffer = uploadBuffer;
	}


	void handle(ObjectInputStream input) {
		// One item at a time.
		while (true) {
			DataItem dataItem;
			try {
				dataItem = (DataItem) input.readObject();
				if (dataItem == null) {
					return;
				}
				dataItem.setTimestamp(new Timestamp(System.currentTimeMillis()));
				uploadBuffer.add(dataItem);
				output(id, dataItem);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static synchronized void output(int id, DataItem dataItem) {
		System.out.print("On Aggregator: Handler " + id + " received ");
		System.out.println(dataItem.getData().length + " bytes at "
				+ dataItem.getTimestamp() + ".");
	}
}

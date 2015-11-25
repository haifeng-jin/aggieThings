
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import common.DataItem;

import sensor.SensorConfig;


/*
 * It simulates a server to handle all the data from one sensor for testing.
 * It runs as a separate thread.
 */
public class ClientHandlerForTests implements Runnable {

	SensorConfig config;
	Socket client;

	public ClientHandlerForTests(SensorConfig config, Socket client) {
		this.config = config;
		this.client = client;
	}

	/*
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			ObjectInputStream input = new ObjectInputStream(client.getInputStream());
			int itemReceived = 0;
			int byteNumReceived = 0;

			//One item at a time.
			while (true) {
				DataItem dataItem = (DataItem) input.readObject();

				if (dataItem == null)
				{
					input.close();
					break;
				}

				itemReceived++;
				byteNumReceived = dataItem.getData().length;
				//Check the size of the current item.
				assertEquals(config.byteNum, byteNumReceived);
			}
			//Check the total number of item received.
			assertEquals(config.itemNum, itemReceived);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}

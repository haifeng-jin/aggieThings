
import static org.junit.Assert.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * It simulates a server to handle all the data from one sensor for testing.
 * It runs as a separate thread.
 */
public class ClientHandler implements Runnable {

	SensorConfig config;
	Socket client;

	public ClientHandler(SensorConfig config, Socket client) {
		this.config = config;
		this.client = client;
	}

	/*
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		DataInputStream input;
		try {
			input = new DataInputStream(client.getInputStream());
			int itemReceived = 0;
			int byteNumReceived = 0;

			//One item at a time.
			while (true) {
				byte[] byteArray = new byte[100];
				byteNumReceived = input.read(byteArray);

				if (byteNumReceived == -1)
					break;

				itemReceived++;
				//Check the size of the current item.
				assertEquals(config.byteNum, byteNumReceived);
			}
			//Check the total number of item received.
			assertEquals(config.itemNum, itemReceived);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

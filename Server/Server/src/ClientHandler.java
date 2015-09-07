import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * It handles all the data from one sensor.
 * It runs as a separate thread.
 */
public class ClientHandler implements Runnable {

	Socket client;
	int id;

	public ClientHandler(Socket client, int id) {
		this.client = client;
		this.id = id;
	}

	/*
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		DataInputStream input;
		try {
			input = new DataInputStream(client.getInputStream());
			int byteNumReceived = 0;

			// One item at a time.
			while (true) {
				byte[] byteArray = new byte[100];
				byteNumReceived = input.read(byteArray);

				if (byteNumReceived == -1)
					break;
				
				insert(byteArray);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void insert(byte[] byteArray) {
		//TODO: do the database insertion here.
		System.out.print("Handler " + id + " received ");
		System.out.println(byteArray.length + "bytes.");
	}
}

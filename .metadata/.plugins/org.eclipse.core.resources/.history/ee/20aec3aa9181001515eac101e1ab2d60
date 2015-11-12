package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import common.DataItem;

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
		try {
			ObjectInputStream input = new ObjectInputStream(client.getInputStream());

			// One item at a time.
			while (true) {
				DataItem dataItem = (DataItem) input.readObject();

				if (dataItem == null)
				{
					input.close();
					break;
				}

				insert(id, dataItem);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	static private synchronized void insert(int id, DataItem dataItem) {
		System.out.print("Handler " + id + " received ");
		System.out.println(dataItem.getData().length + " bytes at " + dataItem.getTimestamp() + ".");
	}
}

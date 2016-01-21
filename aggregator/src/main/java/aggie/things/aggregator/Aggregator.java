package aggie.things.aggregator;

import handler.SensorHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import common.DataItem;
import common.PortInfo;

/*
 * Aggregator receives data from the sensors and upload to the cloud.
 * It can be actuated by calling start() after construction.
 * It attaches timestamps to the DataItem received.  
 */

public class Aggregator {
	BlockingQueue<DataItem> uploadBuffer;
	ArrayList<SensorHandler> handlerList;
	Uploader uploader;

	//bufferSize is the maximum number of DataItems the buffer can hold. 
	Aggregator(int bufferSize) {
		uploadBuffer = new ArrayBlockingQueue<DataItem> (bufferSize);
		handlerList = new ArrayList<SensorHandler>();
		uploader = new Uploader(uploadBuffer);
	}

	void start() {
		new Thread(uploader).start();

		ServerSocket server;
		Socket socket;

		try {
			server = new ServerSocket(PortInfo.getAggregatorPort());
			int handlerCount = 0;

			// Handle one sensor in a separate thread at a time.
			while (true) {
				socket = server.accept();
				SensorHandler handler = new SensorHandler(socket, handlerCount++, uploadBuffer);
				handlerList.add(handler);
				new Thread(handler).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Aggregator aggregator = new Aggregator(10);
		aggregator.start();
	}
}

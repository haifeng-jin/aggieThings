package aggregator;

import handler.SensorHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import common.DataItem;
import common.PortInfo;

public class Aggregator {
	BlockingQueue<DataItem> uploadBuffer;
	ArrayList<SensorHandler> handlerList;
	Uploader uploader;

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
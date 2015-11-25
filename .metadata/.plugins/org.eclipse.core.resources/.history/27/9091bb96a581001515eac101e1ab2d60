

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

import common.PortInfo;

import sensor.Sensor;
import sensor.SensorConfig;
import sensor.SensorFactory;

public class SensorTest {

	/*
	 * Test the data send by one sensor using a ClientHandler.
	 */
	@Test
	public void testRun() {
		SensorConfig config = new SensorConfig(5, 500, 10);
		Sensor sensor = SensorFactory.getSensor(config, "Sensor");
		ServerSocket server;
		Socket socket;

		try {
			server = new ServerSocket(PortInfo.getPort());
			sensor.start();
			socket = server.accept();

			ClientHandlerForTests handler = new ClientHandlerForTests(config, socket);
			//The assertions are in the thread.
			new Thread(handler).start();
			//Wait for the thread to end.
			Thread.sleep(config.itemNum * config.intermissionLength * 2);

			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

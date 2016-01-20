
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import org.junit.Assert;
import org.junit.Test;

import common.DataItem;
import common.PortInfo;

import sensor.Sensor;
import sensor.SensorConfig;
import static org.mockito.Mockito.*;

public class SensorTest {

	/*
	 * Test the data send by one sensor using a ClientHandler.
	 */
	@Test
	public void testRun() throws IOException, ClassNotFoundException, InterruptedException {
		final Socket socket = mock(Socket.class);
		SensorConfig config = new SensorConfig(5, 50, 10);
		Sensor sensor = new Sensor(config, PortInfo.getAddress()) {
			@Override
			protected Socket createSocket() {
				return socket;
			}
		};
		final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		when(socket.getOutputStream()).thenReturn(byteOut);

		sensor.start();
		// Wait for the thread to end.
		Thread.sleep(config.itemNum * config.intermissionLength * 2);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream objectIn = new ObjectInputStream(byteIn);

		int dataItemCount = 0;
		while (true) {
				DataItem data = (DataItem) objectIn.readObject();
				if (data == null)
					break;
				Assert.assertEquals(config.byteNum, data.getData().length);
				dataItemCount++;
		}
		Assert.assertEquals(config.itemNum, dataItemCount);
	}
}

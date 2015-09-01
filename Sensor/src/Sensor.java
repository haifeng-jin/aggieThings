
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/*
 * It simulates a sensor to send data using socket.
 * It runs as a separate thread to send data.
 * The data consists of several items, which is send regularly with equal intermission.
 */

public class Sensor implements Runnable{
	SensorConfig config;
	String serverAddress;
	
	Sensor(SensorConfig config, String address) {
		this.config = config;
		this.serverAddress = address;
	}


	/*
	 * Start the thread.
	 */
	public void start() {
        new Thread(this).start();    
    }    

	/*
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			Socket socketClient;
			PrintStream outputStream;
			socketClient = new Socket(serverAddress, PortInfo.getPort());
			outputStream = new PrintStream(socketClient.getOutputStream());

			//One item at a time.
			for (int i = 0; i < config.itemNum; i++) {
				byte[] byteArray = new byte[config.byteNum];
				outputStream.write(byteArray, 0, config.byteNum);
				Thread.sleep(config.intermissionLength);
			}

			outputStream.close();
			socketClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

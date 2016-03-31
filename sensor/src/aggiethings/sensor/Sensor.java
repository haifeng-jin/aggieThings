package aggiethings.sensor;




import common.DataItem;

/*
 * It simulates a sensor to send data using socket.
 * It runs as a separate thread to send data.
 * The data consists of several items, which is send regularly with equal intermission.
 */

public class Sensor implements Runnable{
	SensorConfig config;
	String serverAddress;
	private boolean running;
	
	public Sensor(SensorConfig config, String address) {
		this.config = config;
		this.serverAddress = address;
	}


	/**
	 * Start the thread.
	 */
	public void start() {
		running = true;
        new Thread(this).start();    
    }    

	public void stop() {
		running = false;
	}

	public void run() {
		try {
			DataPoster poster = new DataPoster(serverAddress);
			//One item at a time.
			while (running) {
				DataItem item = new DataItem(new byte[config.byteNum]);
				item.addTimestamp();
				poster.postJSON(item);
				Thread.sleep(config.intermissionLength);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

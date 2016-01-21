package aggie.things.handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/*
 * Handler is a abstract class which is the superclass for all handlers
 * in socket connections.
 */
public abstract class Handler implements Runnable{
	Socket client;
	int id;

	public Handler(Socket client, int id) {
		this.client = client;
		this.id = id;
	}
	/*
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			ObjectInputStream input = new ObjectInputStream(
					client.getInputStream());
			handle(input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	abstract void handle(ObjectInputStream input);
}

package sensor;

import common.DataItem;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class DataPoster {
	
	private WebTarget target;
	public DataPoster(String address) {
		Client c = ClientBuilder.newClient();
		target = c.target(address);
	}

	public void post(DataItem item) {
		// TODO Auto-generated method stub
		String response = target.path("my").request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(item, MediaType.APPLICATION_JSON), String.class);
		System.out.println(response);
	}

}

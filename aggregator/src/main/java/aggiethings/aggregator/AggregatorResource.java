package aggiethings.aggregator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import common.DataItem;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("aggregator")
public class AggregatorResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public DataItem echo(String itemString) {
    	DataItem item = new DataItem(itemString);
    	Main.buffer.add(item);
    	return item;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DataItem receive(DataItem item) {
    	Main.buffer.add(item);
		return item;
    }
    
    @Path("cost/traffic")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String costTraffic() {
    	return Integer.toString(Main.buffer.getTraffic());
    }

    @Path("cost/storage")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String costStorage() {
    	int ret = Main.buffer.getMaxStorage();
    	return Integer.toString(ret);
    }
}

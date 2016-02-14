package aggiethings.webresource;

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
@Path("cloud")
public class CloudResource {

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
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DataItem query() {
        return Main.cloud.query();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String echo(String item) {
    	return item + " received!";
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DataItem receive(DataItem item) {
    	Main.cloud.insert(item);
    	return item;
    }
    
    @Path("cost/storage")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String cost() {
    	return Integer.toString(Main.cloud.getStorageCost());
    }
}

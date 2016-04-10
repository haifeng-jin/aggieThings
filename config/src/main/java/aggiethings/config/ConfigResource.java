package aggiethings.config;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import aggiethings.common.FileGetter;
import aggiethings.common.PortInfo;

@Path("config")
public class ConfigResource {

    static String configFilePath = "config.json";

	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
    	return "Got it!";
    }
    @Path("file")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getConfigFile() {
		return FileGetter.getContent(configFilePath);
	}

	@Path("address/aggregator/{id}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAggregatorAddress(@PathParam("id") int id) {
		String address = Main.addressManager.getAggregatorAddress(id);
		if (address == null)
			return PortInfo.notAvailable;
        return address;
    }

	@Path("address/aggregator/{id}")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String setAggregatorAddress(@PathParam("id") int id, String address) {
		Main.addressManager.setAggregatorAddress(id, address);
        return address;
    }

	@Path("address/cloud")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getCloudAddress() {
		String address = Main.addressManager.getCloudAddress();
		if (address == null)
			return PortInfo.notAvailable;
        return address;
    }

	@Path("address/cloud")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String setCloudAddress(String address) {
		Main.addressManager.setCloudAddress(address);
        return address;
    }
	
	@Path("time/cloud")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getCloudTimeDiff() {
		return Integer.toString(Main.timeManager.getCloudDiff());
	}

	@Path("time/aggregator/{id}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAggregatorTimeDiff(@PathParam("id") int id) {
		return Integer.toString(Main.timeManager.getAggregatorDiff(id));
	}
	
	@Path("aggregator/id")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAggregatorId() {
		return Integer.toString(Main.aggregatorIdManager.getNewId());
	}
} 
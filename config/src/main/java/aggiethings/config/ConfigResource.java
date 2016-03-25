package aggiethings.config;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import aggiethings.tools.AddressManager;
import aggiethings.tools.TimeManager;
import common.FileGetter;

@Path("config")
public class ConfigResource {

    static String configFilePath = "config.json";

	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @Path("file")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getConfigFile() {
		return FileGetter.getContent(configFilePath);
	}

	@Path("address/aggregator")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAggregatorAddress() {
        return AddressManager.getAggregatorAddress();
    }

	@Path("address/aggregator")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String setAggregatorAddress(String address) {
		AddressManager.setAggregatorAddress(address);
        return address;
    }

	@Path("address/cloud")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getCloudAddress() {
        return AddressManager.getCloudAddress();
    }

	@Path("address/cloud")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String setCloudAddress(String address) {
		AddressManager.setCloudAddress(address);
        return address;
    }
	
	@Path("time/cloud")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getCloudTimeDiff() {
		return Integer.toString(TimeManager.getCloudDiff());
	}

	@Path("time/aggregator")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAggregatorTimeDiff() {
		return Integer.toString(TimeManager.getAggregatorDiff());
	}
} 
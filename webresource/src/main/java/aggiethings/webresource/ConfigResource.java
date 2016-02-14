package aggiethings.webresource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("config/address")
public class ConfigResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	static private String aggregatorAddress = "http://localhost:8080/aggregator";
	static private String cloudAddress = "http://localhost:8080/cloud";

	@Path("aggregator")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAggregatorAddress() {
        return aggregatorAddress;
    }

	@Path("aggregator")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String setAggregatorAddress(String address) {
		aggregatorAddress = address;
        return aggregatorAddress;
    }

	@Path("cloud")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getCloudAddress() {
        return cloudAddress;
    }

	@Path("cloud")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String setCloudAddress(String address) {
		cloudAddress = address;
        return cloudAddress;
    }
} 
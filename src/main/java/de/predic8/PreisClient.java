package de.predic8;

import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.rest.client.inject.*;

import javax.ws.rs.*;

@RegisterRestClient(baseUri="http://localhost:8081")
@Path("/preis")
@Retry(maxRetries = 5, delay = 1000)
public interface PreisClient {

    @Path("/{artikel}")
    @GET
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.2)
    @Fallback(fallbackMethod = "getPreisFailover")
     Preis getPreis(@PathParam("artikel") String artkel);

    default Preis getPreisFailover( String artkel) {
        return new Preis(5.0);
    }

}

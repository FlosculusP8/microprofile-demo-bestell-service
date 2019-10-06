package de.predic8;

import org.eclipse.microprofile.rest.client.inject.*;

import javax.ws.rs.*;

@RegisterRestClient(baseUri="http://localhost:8081")
@Path("/health")
public interface HealthClient {

    @GET
    void health();

}

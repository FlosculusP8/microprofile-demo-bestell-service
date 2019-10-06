package de.predic8;

import org.eclipse.microprofile.metrics.annotation.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/bestellungen")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Timed
public class BestellungenApi {

    @Inject
    @RestClient
    PreisClient ps;

    @POST
    public Preis bestellen(Bestellung best) throws Exception {

         return new Preis(best.getPositionen().stream()
                .map( this::getPreis)
                .reduce( 0.0, Utils::sum));

    }

    double getPreis(Position p) {
        return ps.getPreis(p.getArtikel()).getPreis() * p.getMenge();
    }

}

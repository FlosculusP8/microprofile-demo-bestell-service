package de.predic8;

import org.eclipse.microprofile.health.*;
import org.eclipse.microprofile.rest.client.inject.*;

import javax.enterprise.context.*;
import javax.inject.*;

@Readiness
@ApplicationScoped
public class ServiceDependencyCheck implements HealthCheck {

    @Inject
    @RestClient
    HealthClient hc;

    @Override
    public HealthCheckResponse call() {

        return HealthCheckResponse
                .named("preis-service")
                .state(checkPreisService())
                .build();
    }

    private boolean checkPreisService() {
        try {
            hc.health();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


package ch.juliusbaer.juliusbaerproject.controllers;

import ch.juliusbaer.juliusbaerproject.models.Address;
import ch.juliusbaer.juliusbaerproject.models.Bank;
import ch.juliusbaer.juliusbaerproject.services.AddressService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Summary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    private final Counter requestCount;

    private final Summary requestLatency;

    private final Gauge inprogressRequests;

    public AddressController(CollectorRegistry collectorRegistry) {
        requestCount = Counter.build()
                .name("request_count_address")
                .help("request count for Addresses")
                .register(collectorRegistry);
        requestLatency = Summary.build()
                .name("request_latency_address")
                .help("Latency of address requests")
                .register(collectorRegistry);

        inprogressRequests = Gauge.build()
                .name("inprogress_request_address")
                .help("Number of address requests currently in progress ")
                .register(collectorRegistry);
    }


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    @Operation(summary = "Return address details from API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Items found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Address.class))})})
    @GetMapping
    @Timed(value = "starting_address.time", description = "Time taken to return addresses")
    public String getAll(){
        Summary.Timer requestTimer = requestLatency.startTimer();
        try {
            inprogressRequests.inc();
            requestCount.inc();
            return addressService.getAddresses();

        } finally {
            requestTimer.observeDuration();
            inprogressRequests.dec();
        }
    }





}

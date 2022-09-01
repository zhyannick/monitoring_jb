package ch.juliusbaer.juliusbaerproject.controllers;

import ch.juliusbaer.juliusbaerproject.models.Beer;
import ch.juliusbaer.juliusbaerproject.services.BeerService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beer")
public class BeerController {

    @Autowired
    private BeerService beerService;

    private final Counter requestCount;

    private final Summary requestLatency;

    private final Gauge inprogressRequests;



    public BeerController(CollectorRegistry collectorRegistry) {
        requestCount = Counter.build()
                .name("request_count_beer")
                .help("request count for beer")
                .register(collectorRegistry);
        requestLatency = Summary.build()
                .name("request_latency_beer")
                .help("Latency of beer requests")
                .register(collectorRegistry);
        inprogressRequests = Gauge.build()
                .name("inprogress_request_beer")
                .help("Number of beer requests currently in progress ")
                .register(collectorRegistry);
    }


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    @Operation(summary = "Return Beer details from API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Items found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Beer.class))})})
    @GetMapping
    @Timed(value = "starting_beer.time", description = "Time taken to return Beer")
    public String getAll(){
        Summary.Timer requestTimer = requestLatency.startTimer();
        try {
            inprogressRequests.inc();
            requestCount.inc();
            return beerService.getBeers();
        } finally {
            requestTimer.observeDuration();
            inprogressRequests.dec();
        }
    }
}

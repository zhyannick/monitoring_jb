package ch.juliusbaer.juliusbaerproject.controllers;

import ch.juliusbaer.juliusbaerproject.services.BeerService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    public BeerController(CollectorRegistry collectorRegistry) {
        requestCount = Counter.build()
                .name("request_count_beer")
                .help("request count for beer")
                .register(collectorRegistry);
        requestLatency = Summary.build()
                .name("request_latency_beer")
                .help("Latency of beer requests")
                .register(collectorRegistry);
    }


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    @GetMapping
    @Timed(value = "starting_beer.time", description = "Time taken to return Beer")
    public String getAll(){
        Summary.Timer requestTimer = requestLatency.startTimer();
        try {
            requestCount.inc();
            return beerService.getBeers();
        } finally {
            requestTimer.observeDuration();
        }
    }
}

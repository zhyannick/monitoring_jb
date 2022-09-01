package ch.juliusbaer.juliusbaerproject.controllers;

import ch.juliusbaer.juliusbaerproject.models.Bank;
import ch.juliusbaer.juliusbaerproject.services.BankingService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankingService bankingService;

    private final Counter requestCount;

    private final Summary requestLatency;

    private final Gauge inprogressRequests;

    public BankController(CollectorRegistry collectorRegistry) {
        requestCount = Counter.build()
                .name("request_count_bank")
                .help("request count for bank")
                .register(collectorRegistry);
        requestLatency = Summary.build()
                .name("request_latency_bank")
                .help("Latency of bank requests")
                .register(collectorRegistry);
        inprogressRequests = Gauge.build()
                .name("inprogress_request_bank")
                .help("Number of bank requests currently in progress ")
                .register(collectorRegistry);
    }

    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    @Operation(summary = "Return banking details from API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Items found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Bank.class))})})
    @GetMapping
    @Timed(value = "starting_bank.time", description = "Time taken to return bank")
    public String getAll(){
        Summary.Timer requestTimer = requestLatency.startTimer();
        try {
            inprogressRequests.inc();
            requestCount.inc();
            return bankingService.getBankDetails();
        } finally {
            requestTimer.observeDuration();
            inprogressRequests.dec();
        }
    }

    @GetMapping("/test")
    public String hello(){
        return "hello";
    }
}


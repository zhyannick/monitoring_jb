package ch.juliusbaer.juliusbaerproject.controllers;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final Counter requestCount;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public BankController(CollectorRegistry collectorRegistry) {
        requestCount = Counter.build()
                .name("request_bank")
                .help("request count for bank")
                .register(collectorRegistry);
    }


    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }




    @GetMapping
    @Timed(value = "starting_bank.time", description = "Time taken to return bank")
    public String getAll(){


        try {
            String uri = "https://random-data-api.com/api/v2/banks?size=1";
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            requestCount.inc();
            return result;

        } catch (Exception e){

            throw e;

        }
    }
}

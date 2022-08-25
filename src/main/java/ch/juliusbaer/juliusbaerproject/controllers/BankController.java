package ch.juliusbaer.juliusbaerproject.controllers;

import ch.juliusbaer.juliusbaerproject.models.Bank;
import ch.juliusbaer.juliusbaerproject.services.BankingService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bank")
public class BankController {


    @Autowired
    private BankingService bankingService;


    private final Counter requestCount;


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
            requestCount.inc();
            return bankingService.getBankDetails();



        } catch (Exception e){

            throw e;

        }
    }

    @GetMapping("/test")
    public String hello(){
        return "hello";
    }
}


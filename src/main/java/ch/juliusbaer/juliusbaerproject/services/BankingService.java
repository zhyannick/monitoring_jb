package ch.juliusbaer.juliusbaerproject.services;

import ch.juliusbaer.juliusbaerproject.models.Bank;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankingService {


    private final RestTemplate restTemplate;

    private final String URL = "https://random-data-api.com/api/v2/banks?size=30";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public BankingService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getBankDetails() {
        return this.restTemplate.getForObject(URL, String.class);
    }
}

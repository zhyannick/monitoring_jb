package ch.juliusbaer.juliusbaerproject.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BeerService {

    private final RestTemplate restTemplate;

    private final String URL = "https://random-data-api.com/api/v2/beers?size=30";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public BeerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getBeers() {
        return this.restTemplate.getForObject(URL, String.class);
    }
}

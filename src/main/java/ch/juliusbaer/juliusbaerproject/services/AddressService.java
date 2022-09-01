package ch.juliusbaer.juliusbaerproject.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {

    private final RestTemplate restTemplate;

    private final String URL = "https://random-data-api.com/api/v2/addresses?size=30";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public AddressService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getAddresses() {
        return this.restTemplate.getForObject(URL, String.class);
    }
}

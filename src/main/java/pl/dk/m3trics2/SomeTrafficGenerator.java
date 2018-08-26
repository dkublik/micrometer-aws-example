package pl.dk.m3trics2;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class SomeTrafficGenerator {

    private final RestTemplate restTemplate;

    SomeTrafficGenerator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    void makeSomeHit() {
        restTemplate.getForObject("http://localhost:8080/lucky-number", Integer.class);
    }
}
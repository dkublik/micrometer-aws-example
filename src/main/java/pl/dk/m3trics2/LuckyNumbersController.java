package pl.dk.m3trics2;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@EnableScheduling
@RequestMapping("/lucky-number")
class LuckyNumbersController {

    private final AtomicInteger currentLucky;

    LuckyNumbersController(MeterRegistry meterRegistry) {
        currentLucky = meterRegistry.gauge("currentLucky", new AtomicInteger(0));
    }

    @GetMapping
    Integer luckyNumber() {
        int luckyNumber = (int)(Math.random() * 100);
        currentLucky.set(luckyNumber);
        littleSleep(); // to randomize http.server.requests.avg metric
        return luckyNumber;
    }

    private void littleSleep() {
        try {
            Thread.sleep((int)(Math.random() * 100));
        } catch (InterruptedException e) {
            // do nothing
        }
    }
}

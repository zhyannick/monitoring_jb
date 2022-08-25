package ch.juliusbaer.juliusbaerproject;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class JuliusbaerProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JuliusbaerProjectApplication.class, args);
    }



}

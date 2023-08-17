package com.nashtech;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class EventHubDemo {
    /**
     * This method is the entry point for the JavaCompetencyDemoApplication.
     * It calls the springApplication.run method to start the application.
     *
     * @param args an array of command-line arguments passed to
     *                the application
     */
    public static void main(final String[] args) {
        SpringApplication.run(EventHubDemo.class, args);
    }
}

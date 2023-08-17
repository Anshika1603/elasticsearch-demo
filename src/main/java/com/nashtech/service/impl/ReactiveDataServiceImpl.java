package com.nashtech.service.impl;

import com.nashtech.model.Car;
import com.nashtech.repository.KafkaConsumerRepository;
import com.nashtech.service.ReactiveDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Flux;

/**
 * Service class for handling car-related operations.
 */
@Slf4j
@Service
public class ReactiveDataServiceImpl implements
        ReactiveDataService {

    /**
     * WebClient instance for making HTTP requests to the external API.
     */
    @Autowired
    private KafkaConsumerRepository kafkaConsumerRepository;

    public Iterable<Car> findAllCars() {
        log.info("-------Data received from eventhub-------");
        return kafkaConsumerRepository.findAll();
    }

    @KafkaListener(topics = "eventhub", groupId = "elasticsearch", concurrency = "2")
    public void carNotificationConsumer(Car event) {
        kafkaConsumerRepository.save(event);
        log.info("Cars event received for notification => {}", event);
    }

}

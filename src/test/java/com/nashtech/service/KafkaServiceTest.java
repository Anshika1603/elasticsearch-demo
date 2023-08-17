package com.nashtech.service;

import com.nashtech.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class KafkaServiceTest {

    @Mock
    private KafkaTemplate<String, Car> kafkaTemplate;

    @InjectMocks
    private KafkaService kafkaService;

    @BeforeEach
    void setUp() {
        // Initialize the mocks before each test method
        reset(kafkaTemplate);
    }

    @Test
    void testPushData() {
        // Arrange
        Car car = new Car(0, "brand", "model", 2020L, "color", 0.0, 0.0);

        // Mock the behavior of kafkaTemplate.send() using doAnswer()
        doAnswer(invocation -> {
            Message<Car> message = invocation.getArgument(0);
            // Perform any additional verification/assertion on the message if needed
            return null; // Return null since the method is void
        }).when(kafkaTemplate).send(any(Message.class));

        // Act
        Mono<Void> result = kafkaService.pushData(car);

        // Assert
        // Verify that kafkaTemplate.send() was called with the correct message
        verify(kafkaTemplate, times(1)).send(any(Message.class));

        // Verify that the Mono returned by the pushData method is empty
        StepVerifier.create(result)
                .expectSubscription()
                .expectComplete()
                .verify();
    }
}


package com.nashtech.config;

import com.nashtech.model.Car;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableKafka
@Configuration
public class kafkaConsumerConfig {
    private static final String EVENT_HUB_NAMESPACE = "az-nashtech-eventhub";
    private static final String EVENT_HUB_NAME = "eventhub";
    private static final String SAS_KEY_NAME = "trigger";
    private static final String SAS_KEY = "Endpoint=sb://az-nashtech-eventhub.servicebus.windows.net/;SharedAccessKeyName=trigger;SharedAccessKey=NAXpkXgXYIIxqkyNIXRcovM7eWA9QnJwW+AEhLC4jXY=";

    @Bean
    public ConsumerFactory<String, Car> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, EVENT_HUB_NAMESPACE + ".servicebus.windows.net:9093");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "elasticsearch");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Car.class.getName());
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put("sasl.mechanism", "PLAIN");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required "
                + "username=\"$ConnectionString\" password=\"" + SAS_KEY + "\";");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Car>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Car> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}


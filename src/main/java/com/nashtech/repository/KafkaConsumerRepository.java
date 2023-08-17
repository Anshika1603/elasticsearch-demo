package com.nashtech.repository;

import com.nashtech.model.Car;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaConsumerRepository extends ElasticsearchRepository<Car, String> {
}

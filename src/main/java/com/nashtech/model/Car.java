package com.nashtech.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Represents a reactive data model for sending
 * details of the Car to the CosmosDB.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(indexName = "car-collection")
public class Car {

    @Id
    private String id;
    /**
     * The card ID of the car.
     */
    @Field(type = FieldType.Integer, name = "carId")
    private Integer carId;

    /**
     * The brand of the car.
     */
    @Field(type = FieldType.Text, name = "brand")
    private String brand;

    /**
     * The model of the car.
     */
    @Field(type = FieldType.Text, name = "model")
    private String model;

    /**
     * The year of manufacture of the car.
     */
    @Field(type = FieldType.Long, name = "year")
    private Long year;

    /**
     * The color of the car.
     */
    @Field(type = FieldType.Text, name = "color")
    private String color;

    /**
     * The mileage of the car in kilometers.
     */
    @Field(type = FieldType.Double, name = "mileage")
    private Double mileage;

    /**
     * The price of the car.
     */
    @Field(type = FieldType.Double, name = "price")
    private Double price;
}

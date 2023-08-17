package com.nashtech.service;

import com.nashtech.model.Car;

/**
 * Interface representing a service for performing reactive data access
 * operations on cars.
 * It provides methods for obtaining cars with a specific brand and getting
 * distinct car brands in a reactive manner.
 */
public interface ReactiveDataService {

    Iterable<Car> findAllCars();

}

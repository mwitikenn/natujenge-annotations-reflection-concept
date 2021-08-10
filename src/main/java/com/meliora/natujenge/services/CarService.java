package com.meliora.natujenge.services;

import com.meliora.natujenge.annotations.Autowired;
import com.meliora.natujenge.annotations.Service;
import com.meliora.natujenge.models.CarModel;
import com.meliora.natujenge.repositories.CarRepository;

@Service
public class CarService {

    @Autowired
    public CarRepository carRepository;

    public void Save(CarModel carModel) {
        System.out.println("Service layer.... About to save the car object");

        carRepository.save(carModel);
        System.out.println("Saved car model...");
    }
}

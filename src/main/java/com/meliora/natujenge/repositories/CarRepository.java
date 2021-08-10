package com.meliora.natujenge.repositories;

import com.meliora.natujenge.annotations.Repository;
import com.meliora.natujenge.models.CarModel;

import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CarRepository {

    AtomicInteger requests = new AtomicInteger(0);

    public boolean save(CarModel carModel) {
        requests.incrementAndGet();
        System.out.println("Repository layer |requestNumber : " + requests + " .... About to save car model " + carModel);

        return true;
    }
}

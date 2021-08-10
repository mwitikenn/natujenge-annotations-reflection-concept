package com.meliora.natujenge.repositories;

import com.meliora.natujenge.models.CarModel;

public class CarRepostory2 implements CrudRepository<CarModel, Integer> {

    @Override
    public void save(CarModel carModel) {

    }

    @Override
    public CarModel findByID(Integer integer) {
        return null;
    }
}

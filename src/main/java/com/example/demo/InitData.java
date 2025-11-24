package com.example.demo;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    private final CarRepository carRepository;

    public InitData(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) {
        carRepository.save(new Car("BMW", 220));
        carRepository.save(new Car("Audi", 200));
        carRepository.save(new Car("Volvo", 180));

        System.out.println("Cars in DB: " + carRepository.count());
    }
}

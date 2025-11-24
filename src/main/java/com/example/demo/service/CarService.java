package com.example.demo.service;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.transaction.Transactional;

@Service
public class CarService {
	private int speed = 0;

	private final CarRepository carRepository;

	public CarService(CarRepository carRepository){
		this.carRepository = carRepository;
	}

	@Transactional
	public String start(){
		System.out.println("Engine start в транзакции");
		return "Engine start";
	}
	@Transactional
	public void setSpeed(int speed){
		this.speed = speed;
		System.out.println("Set speed to: " + speed + " (в транзакции)");
	}
	public int getSpeed(){
		return speed;
	}

	@PostConstruct
	public void init(){
		System.out.println("CarSrvice создан и готов к работе!");
		System.out.println("Текущая скорость = " + speed);
	}

	@PreDestroy
	public void destroy(){
		System.out.println("CarService уничтожается...");
		System.out.println("Текущая скорость = " + speed);
	}


	public List<Car> getAllCars(){
		return carRepository.findAll();
	}

	public Car getCar(Long id){
		return carRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Car not found"));
	}

	public Car addCar(String model, int speed){
		Car car = new Car(model, speed);
		return carRepository.save(car);
	}

	public void deleteCar(Long id){
		carRepository.deleteById(id);
	}


	@Transactional
	public Car updateCar(Long id, int speed){
		Car car = getCar(id);
		car.setSpeed(speed);
		return car;
	}
}

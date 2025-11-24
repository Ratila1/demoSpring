package com.example.demo.controller;

import java.util.List;

import javax.management.monitor.StringMonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Car;
import com.example.demo.service.CarService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/car")
public class CarController {
	private final CarService carService;
	public CarController(CarService carService){
		this.carService = carService;
	}

	@GetMapping("/start")
	public String start() {
		return carService.start();
	}

	@PostMapping("/speed")
	public void setSpeed(@RequestParam int value) {
		carService.setSpeed(value);
	}
	
	@GetMapping("/status")
	public int getStatus() {
		return carService.getSpeed();
	}

	@PostMapping("/add-speed")
	public int addSpeed(@RequestParam int delta) {
		carService.setSpeed(carService.getSpeed() + delta);
		return carService.getSpeed();
	}

	@PostMapping("/multiply-speed")
	public int multiplySpeed(@RequestParam int factor) {
		carService.setSpeed(carService.getSpeed() * factor);
		return carService.getSpeed();
	}


	@GetMapping("/all")
	public List<Car> all() {
		return carService.getAllCars();
	}

	@GetMapping("/{id}")
	public Car one(@PathVariable Long id){
		return carService.getCar(id);
	}

	@PostMapping("/add")
	public Car add(@RequestParam String model, @RequestParam int speed){
		return carService.addCar(model, speed);
	}

	@PostMapping("/update")
	public Car update(@RequestParam Long id, @RequestParam int speed){
		return carService.updateCar(id, speed);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id){
		carService.deleteCar(id);
	}
	
	
}

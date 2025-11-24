package com.example.demo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.example.demo.controller.CarController;
import com.example.demo.service.CarService;

public class CarControllerTest {
    private CarService carService;
    private CarController carController;

    @BeforeEach
    void setup(){
        carService = mock(CarService.class);
        carController = new CarController(carService);
    }

    @Test
    void testStart(){
        when(carService.start()).thenReturn("Engine start");

        String result = carController.start();
        assertEquals("Engine start", result);

        verify(carService).start();
    }

    @Test
    void testSetSpeedArgumentCaptor(){
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        carController.setSpeed(100);

        verify(carService).setSpeed(captor.capture());
        assertEquals(100, captor.getValue());
    }
}

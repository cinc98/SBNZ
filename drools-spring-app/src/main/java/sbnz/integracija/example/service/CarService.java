package sbnz.integracija.example.service;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Car;
import sbnz.integracija.example.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	// private final KieContainer kieContainer;

	@Autowired
	public CarService(KieContainer kieContainer) {
		// this.kieContainer = kieContainer;

	}

	public List<Car> getAll() {
		return carRepository.findAll();
	}

}
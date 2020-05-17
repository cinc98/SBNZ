package sbnz.integracija.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.Car;
import sbnz.integracija.example.repository.CarRepository;
import sbnz.integracija.example.service.CarService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("car")
public class CarController {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private CarService carService;
	


	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Car> getAll() {

		return carService.getAll();
	}
	
	@RequestMapping(value = "/model", method = RequestMethod.GET, produces = "application/json")
	public List<Car> getAllByName(@RequestParam("model") String name) {

		return carRepository.findByName(name);
	}
}
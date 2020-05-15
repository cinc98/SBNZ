package sbnz.integracija.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sbnz.integracija.example.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

	List<Car> findAll();

	Car findOneById(int id);

	@Transactional
	@Modifying
	@Query("update Car p set p.number = :number where p.id = :id")
	int updateCarNumber(@Param("number") int number, @Param("id") int id);

	@Transactional
	@Modifying
	@Query("update Car p set p.searched = :searched where p.id = :id")
	int updateCarSearched(@Param("searched") int searched, @Param("id") int id);
}
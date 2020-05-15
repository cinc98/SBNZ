package sbnz.integracija.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sbnz.integracija.example.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findAll();

	Reservation findOneById(int id);

	List<Reservation> findByUserId(int userId);

	List<Reservation> findByCarId(int carId);

<<<<<<< HEAD
	

=======
<<<<<<< HEAD:drools-spring-app/src/main/java/sbnz/integracija/example/repository/ReservationRepository.java
	

=======
>>>>>>> 529319fafa8044764586ec1a50aba54929f52930:drools-spring-app/src/main/java/repository/ReservationRepository.java
>>>>>>> 978cc5ccb0b90631a2c9c26e526f0bd78620a3ca
	@Transactional
	@Modifying
	@Query("update Reservation p set p.status = :status, p.penaltyPercentage =:penalty where p.id = :id")
	int updateStatus(@Param("status") String status,@Param("penalty") int penalty, @Param("id") int id);

}

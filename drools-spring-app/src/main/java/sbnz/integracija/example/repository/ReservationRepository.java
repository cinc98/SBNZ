package sbnz.integracija.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sbnz.integracija.example.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	List<Reservation> findAll();

	List<Reservation> findByUserId(int userId);
	List<Reservation> findByCarId(int carId);
	
	@Transactional
	@Modifying
	@Query("update Reservation p set p.status = :status where p.id = :id")
	int updateStatus(@Param("status") int status, @Param("id") int id);

}

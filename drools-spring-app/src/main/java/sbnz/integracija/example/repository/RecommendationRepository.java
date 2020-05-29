package sbnz.integracija.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.integracija.example.model.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long>{
	List<Recommendation> findAll();
}

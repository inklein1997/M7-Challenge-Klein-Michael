package edu.smu.musicstorerecommendations.repository;

import edu.smu.musicstorerecommendations.dto.LabelRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRecommendationRepository extends JpaRepository<LabelRecommendation, Integer> {
}

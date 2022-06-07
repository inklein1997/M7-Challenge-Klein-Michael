package edu.smu.musicstorerecommendations.repository;

import edu.smu.musicstorerecommendations.dto.AlbumRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRecommendationRepository extends JpaRepository<AlbumRecommendation, Integer> {
}

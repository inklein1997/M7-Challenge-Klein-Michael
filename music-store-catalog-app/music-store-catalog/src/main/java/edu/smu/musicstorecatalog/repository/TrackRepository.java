package edu.smu.musicstorecatalog.repository;

import edu.smu.musicstorecatalog.dto.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {
}

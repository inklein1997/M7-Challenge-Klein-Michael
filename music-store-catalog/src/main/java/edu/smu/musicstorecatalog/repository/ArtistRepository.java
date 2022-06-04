package edu.smu.musicstorecatalog.repository;

import edu.smu.musicstorecatalog.dto.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}

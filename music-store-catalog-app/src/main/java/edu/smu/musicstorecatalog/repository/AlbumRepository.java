package edu.smu.musicstorecatalog.repository;

import edu.smu.musicstorecatalog.dto.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
}

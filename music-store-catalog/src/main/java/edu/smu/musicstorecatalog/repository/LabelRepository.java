package edu.smu.musicstorecatalog.repository;

import edu.smu.musicstorecatalog.dto.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {
}

package edu.smu.musicstorerecommendations.controller;

import edu.smu.musicstorerecommendations.dto.LabelRecommendation;
import edu.smu.musicstorerecommendations.exception.QueryNotFoundException;
import edu.smu.musicstorerecommendations.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class LabelRecommendationController {

    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/labelRecommendation")
    @ResponseStatus(HttpStatus.OK)
    public List<LabelRecommendation> getAllLabelRecommendation() {
        return serviceLayer.getAllLabelRecommendations();
    }

    @GetMapping("/labelRecommendation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<LabelRecommendation> getLabelRecommendationById(@PathVariable int id) {
        if (serviceLayer.getLabelRecommendationById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No label recommendation with that ID exists");
        }
        return serviceLayer.getLabelRecommendationById(id);
    }

    @PostMapping("/labelRecommendation")
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendation createLabelRecommendation(@Valid @RequestBody LabelRecommendation labelRecommendation) {
        return serviceLayer.createLabelRecommendation(labelRecommendation);
    }

    @PutMapping("/labelRecommendation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabelRecommendation(@Valid @PathVariable int id, @RequestBody LabelRecommendation labelRecommendation) {
        if (id != labelRecommendation.getLabelRecommendationId()) {
            throw new DataIntegrityViolationException("Your request body ID does not match your Path Variable ID");
        }
        serviceLayer.updateLabelRecommendation(labelRecommendation);
    }

    @DeleteMapping("/labelRecommendation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabelRecommendation(@PathVariable int id) {
        if (serviceLayer.getLabelRecommendationById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No label recommendation with that ID exists");
        }
        serviceLayer.deleteLabelRecommendation(id);
    }
}


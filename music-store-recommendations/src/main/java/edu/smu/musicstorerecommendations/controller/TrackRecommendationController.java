package edu.smu.musicstorerecommendations.controller;

import edu.smu.musicstorerecommendations.dto.TrackRecommendation;
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
public class TrackRecommendationController {
    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/trackRecommendation")
    @ResponseStatus(HttpStatus.OK)
    public List<TrackRecommendation> getAllTrackRecommendations() {
        return serviceLayer.getAllTrackRecommendations();
    }

    @GetMapping("/trackRecommendation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TrackRecommendation> getTrackRecommendationById(@PathVariable int id) {
        if (serviceLayer.getTrackRecommendationById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No track recommendation with that ID exists");
        }
        return serviceLayer.getTrackRecommendationById(id);
    }

    @PostMapping("/trackRecommendation")
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendation createTrackRecommendation(@Valid @RequestBody TrackRecommendation trackRecommendation) {
        return serviceLayer.createTrackRecommendation(trackRecommendation);
    }

    @PutMapping("/trackRecommendation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrackRecommendation(@Valid @PathVariable int id, @RequestBody TrackRecommendation trackRecommendation) {
        if (id != trackRecommendation.getTrackRecommendationId()) {
            throw new DataIntegrityViolationException("Your request body ID does not match your Path Variable ID");
        }
        serviceLayer.updateTrackRecommendation(trackRecommendation);
    }

    @DeleteMapping("/trackRecommendation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrackRecommendation(@PathVariable int id) {
        if (serviceLayer.getTrackRecommendationById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No track recommendation with that ID exists");
        }
        serviceLayer.deleteTrackRecommendation(id);
    }
}

package edu.smu.musicstorerecommendations.controller;

import edu.smu.musicstorerecommendations.dto.AlbumRecommendation;
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
public class AlbumRecommendationController {
    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/albumRecommendation")
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecommendation> getAllAlbumRecommendations() {
        return serviceLayer.getAllAlbumRecommendations();
    }

    @GetMapping("/albumRecommendation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<AlbumRecommendation> getAlbumRecommendationById(@PathVariable int id) {
        if (serviceLayer.getAlbumRecommendationById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No album recommendation with that ID exists");
        }
        return serviceLayer.getAlbumRecommendationById(id);
    }

    @PostMapping("/albumRecommendation")
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendation createAlbumRecommendation(@Valid @RequestBody AlbumRecommendation albumRecommendation) {
        return serviceLayer.createAlbumRecommendation(albumRecommendation);
    }

    @PutMapping("/albumRecommendation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbumRecommendation(@Valid @PathVariable int id, @RequestBody AlbumRecommendation albumRecommendation) {
        if (id != albumRecommendation.getAlbumRecommendationId()) {
            throw new DataIntegrityViolationException("Your request body ID does not match your Path Variable ID");
        }
        serviceLayer.updateAlbumRecommendation(albumRecommendation);
    }

    @DeleteMapping("/albumRecommendation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumRecommendation(@PathVariable int id) {
        if (serviceLayer.getAlbumRecommendationById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No album recommendation with that ID exists");
        }
        serviceLayer.deleteAlbumRecommendation(id);
    }
}

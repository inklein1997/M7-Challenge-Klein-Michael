package edu.smu.musicstorerecommendations.controller;


import edu.smu.musicstorerecommendations.dto.ArtistRecommendation;
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
public class ArtistRecommendationController {

    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/artistRecommendation")
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistRecommendation> getAllArtistRecommendations() {
        return serviceLayer.getAllArtistRecommendations();
    }

    @GetMapping("/artistRecommendation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ArtistRecommendation> getArtistRecommendationById(@PathVariable int id) {
        if (serviceLayer.getArtistRecommendationById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No artist recommendation with that ID exists");
        }
        return serviceLayer.getArtistRecommendationById(id);
    }

    @PostMapping("/artistRecommedation")
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistRecommendation createArtistRecommendation(@Valid @RequestBody ArtistRecommendation artistRecommendation) {
        return serviceLayer.createArtistRecommendation(artistRecommendation);
    }

    @PutMapping("/artistRecommendation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtistRecommendation(@Valid @PathVariable int id, @RequestBody ArtistRecommendation artistRecommendation) {
        if (id != artistRecommendation.getArtistRecommendationId()) {
            throw new DataIntegrityViolationException("Your request body ID does not match your Path Variable ID");
        }
        serviceLayer.updateArtistRecommendation(artistRecommendation);
    }

    @DeleteMapping("/artistRecommendation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtistRecommendation(@PathVariable int id) {
        if (serviceLayer.getArtistRecommendationById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No artist recommendation with that ID exists");
        }
        serviceLayer.deleteArtistRecommendation(id);
    }
}

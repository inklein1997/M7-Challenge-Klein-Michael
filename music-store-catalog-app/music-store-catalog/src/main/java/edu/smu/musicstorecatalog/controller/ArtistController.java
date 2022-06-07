package edu.smu.musicstorecatalog.controller;

import edu.smu.musicstorecatalog.dto.Artist;
import edu.smu.musicstorecatalog.exception.QueryNotFoundException;
import edu.smu.musicstorecatalog.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ArtistController {

    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/artist")
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> getAllArtists() {
        return serviceLayer.getAllArtists();
    }

    @GetMapping("/artist/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Artist> getArtistById(@PathVariable int id) {
        if (serviceLayer.getArtistById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No artist with that ID exists");
        }
        return serviceLayer.getArtistById(id);
    }

    @PostMapping("/artist")
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@Valid @RequestBody Artist artist) {
        return serviceLayer.createArtist(artist);
    }

    @PutMapping("/artist/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtist(@PathVariable int id, @Valid @RequestBody Artist artist) {
        if (id != artist.getArtistId()) {
            throw new DataIntegrityViolationException("Your request body ID does not match your Path Variable ID");
        }
        serviceLayer.updateArtist(artist);
    }

    @DeleteMapping("/artist/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable int id) {
        if (serviceLayer.getArtistById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No artist with that ID exists");
        }
        serviceLayer.deleteArtist(id);
    }
}

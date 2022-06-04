package edu.smu.musicstorecatalog.controller;

import edu.smu.musicstorecatalog.dto.Track;
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
public class TrackController {
    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/track")
    @ResponseStatus(HttpStatus.OK)
    public List<Track> getAllTracks() {
        return serviceLayer.getAllTracks();
    }

    @GetMapping("/track/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Track> getTrackById(@PathVariable int id) {
        if (serviceLayer.getTrackById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No track with that ID exists");
        }
        return serviceLayer.getTrackById(id);
    }

    @PostMapping("/track")
    @ResponseStatus(HttpStatus.CREATED)
    public Track createTrack(@Valid @RequestBody Track track) {
        return serviceLayer.createTrack(track);
    }

    @PutMapping("/track/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrack(@Valid @PathVariable int id, @RequestBody Track track) {
        if (id != track.getTrackId()) {
            throw new DataIntegrityViolationException("Your request body ID does not match your Path Variable ID");
        }
        serviceLayer.updateTrack(track);
    }

    @DeleteMapping("/track/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrack(@PathVariable int id) {
        if (serviceLayer.getTrackById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No track with that ID exists");
        }
        serviceLayer.deleteTrack(id);
    }
}

package edu.smu.musicstorecatalog.controller;

import edu.smu.musicstorecatalog.dto.Album;
import edu.smu.musicstorecatalog.exception.QueryNotFoundException;
import edu.smu.musicstorecatalog.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AlbumController {
    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/album")
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAllAlbums() {
        return serviceLayer.getAllAlbums();
    }

    @GetMapping("/album/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Album> getAlbumById(@PathVariable int id) {
        if (serviceLayer.getAlbumById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No album with that ID exists");
        }
        return serviceLayer.getAlbumById(id);
    }

    @PostMapping("/album")
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@Valid @RequestBody Album album) {
        return serviceLayer.createAlbum(album);
    }

    @PutMapping("/album/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbum(@Valid @PathVariable int id, @RequestBody Album album) {
        if (id != album.getAlbumId()) {
            throw new DataIntegrityViolationException("Your request body ID does not match your Path Variable ID");
        }
        serviceLayer.updateAlbum(album);
    }

    @DeleteMapping("/album/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable int id) {
        if (serviceLayer.getAlbumById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No album with that ID exists");
        }
        serviceLayer.deleteAlbum(id);
    }
}

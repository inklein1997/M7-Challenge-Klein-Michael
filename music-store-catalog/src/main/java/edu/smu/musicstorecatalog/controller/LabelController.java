package edu.smu.musicstorecatalog.controller;

import edu.smu.musicstorecatalog.dto.Label;
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
public class LabelController {

    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/label")
    @ResponseStatus(HttpStatus.OK)
    public List<Label> getAllLabels() {
        return serviceLayer.getAllLabels();
    }

    @GetMapping("/label/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Label> getLabelById(@PathVariable int id) {
        if (serviceLayer.getLabelById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No label with that ID exists");
        }
        return serviceLayer.getLabelById(id);
    }

    @PostMapping("/label")
    @ResponseStatus(HttpStatus.CREATED)
    public Label createLabel(@Valid @RequestBody Label label) {
        return serviceLayer.createLabel(label);
    }

    @PutMapping("/label/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@Valid @PathVariable int id, @RequestBody Label label) {
        if (id != label.getLabelId()) {
            throw new DataIntegrityViolationException("Your request body ID does not match your Path Variable ID");
        }
        serviceLayer.updateLabel(label);
    }

    @DeleteMapping("/label/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable int id) {
        if (serviceLayer.getLabelById(id).orElse(null) == null) {
            throw new QueryNotFoundException("No label with that ID exists");
        }
        serviceLayer.deleteLabel(id);
    }
}


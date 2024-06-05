package fr.fms.apitrainings.web;

import fr.fms.apitrainings.business.IBusinessImpl;
import fr.fms.apitrainings.dao.CategoryRepository;
import fr.fms.apitrainings.entities.Category;
import fr.fms.apitrainings.entities.Training;
import fr.fms.apitrainings.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

/**
 * Controller class for handling training-related requests.
 */
@CrossOrigin("*")
@RestController
public class TrainingController {

    @Autowired
    private IBusinessImpl iBusiness;

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Endpoint for retrieving all trainings.
     *
     * @return the list of all trainings.
     */
    @GetMapping("/trainings")
    public List<Training> allTrainings() {
        return iBusiness.getTrainings();
    }

    /**
     * Endpoint for saving a training.
     *
     * @param training the training to save.
     * @return ResponseEntity containing the saved training.
     */
    @PostMapping("/trainings")
    public ResponseEntity<Training> saveTraining(@RequestBody Training training) {
        Training training1 = iBusiness.saveTraining(training);
        if (Objects.isNull(training1)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(training1.getId())
                .toUri();
        return ResponseEntity.created(location).body(training1);
    }

    /**
     * Endpoint for deleting a training by ID.
     *
     * @param id the ID of the training to delete.
     */
    @DeleteMapping("/trainings/{id}")
    public void deleteTraining(@PathVariable("id") Long id) {
        System.out.println("Suppression ok");
        iBusiness.deleteTraining(id);
    }

    /**
     * Endpoint for retrieving a training by ID.
     *
     * @param id the ID of the training to retrieve.
     * @return the retrieved training.
     * @throws RecordNotFoundException if the training with the given ID does not exist.
     */
    @GetMapping("/training/{id}")
    public Training getTrainingById(@PathVariable("id") Long id) {
        return iBusiness.getTrainingById(id)
                .orElseThrow(() -> new RecordNotFoundException("Id de Formation " + id + " n'existe pas"));
    }

    /**
     * Endpoint for retrieving trainings by category ID.
     *
     * @param id the ID of the category.
     * @return the list of trainings belonging to the specified category.
     */
    @GetMapping("/trainings/category/{id}")
    public List<Training> getTrainingByCategory(@PathVariable("id") Long id) {
        return iBusiness.getTrainingsByCategory(id);
    }

    /**
     * Endpoint for retrieving all categories.
     *
     * @return the list of all categories.
     */
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return iBusiness.getCategories();
    }

}

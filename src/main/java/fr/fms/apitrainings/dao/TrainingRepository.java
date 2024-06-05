package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.Category;
import fr.fms.apitrainings.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Training entities.
 * Provides CRUD operations and other query methods for Training objects.
 */
@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Retrieves a list of trainings by category.
     *
     * @param category the category to filter trainings by.
     * @return a list of trainings belonging to the specified category.
     */
    public List<Training> findByCategory(Category category);
}

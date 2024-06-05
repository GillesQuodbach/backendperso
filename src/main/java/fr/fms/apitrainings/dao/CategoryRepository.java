package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Category entities.
 * Provides CRUD operations and other query methods for Category objects.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Customer entities.
 * Provides CRUD operations and other query methods for Customer objects.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

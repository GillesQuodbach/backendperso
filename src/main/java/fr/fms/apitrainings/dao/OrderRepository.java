package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Order entities.
 * Provides CRUD operations and other query methods for Order objects.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}

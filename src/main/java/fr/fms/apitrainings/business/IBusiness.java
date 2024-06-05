package fr.fms.apitrainings.business;

import fr.fms.apitrainings.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Business interface for handling various operations related to trainings, categories, customers, orders, and order items.
 */
public interface IBusiness {

    /**
     * Retrieves all trainings.
     *
     * @return a list of all trainings.
     */
    List<Training> getTrainings();

    /**
     * Retrieves all trainings by category.
     *
     * @param idCategory the ID of the category.
     * @return a list of trainings in the specified category.
     */
    List<Training> getTrainingsByCategory(Long idCategory);

    /**
     * Retrieves a training by its ID.
     *
     * @param idTraining the ID of the training.
     * @return an Optional containing the training if found, or empty if not found.
     */
    Optional<Training> getTrainingById(Long idTraining);

    /**
     * Saves a training.
     *
     * @param training the training to save.
     * @return the saved training.
     */
    Training saveTraining(Training training);

    /**
     * Deletes a training by its ID.
     *
     * @param idTraining the ID of the training to delete.
     */
    void deleteTraining(Long idTraining);

    /**
     * Retrieves all categories.
     *
     * @return a list of all categories.
     */
    List<Category> getCategories();

    /**
     * Saves a customer.
     *
     * @param customer the customer to save.
     * @return the saved customer.
     */
    Customer saveCustomer(Customer customer);

    /**
     * Retrieves a customer by its ID.
     *
     * @param idCustomer the ID of the customer.
     * @return an Optional containing the customer if found, or empty if not found.
     */
    Optional<Customer> getCustomerById(Long idCustomer);

    /**
     * Saves an order.
     *
     * @param order the order to save.
     * @return the saved order.
     */
    Order saveOrder(Order order);

    /**
     * Saves an order item.
     *
     * @param orderItem the order item to save.
     * @return the saved order item.
     */
    OrderItem saveOrderItem(OrderItem orderItem);
}

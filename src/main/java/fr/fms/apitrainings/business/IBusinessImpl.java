package fr.fms.apitrainings.business;

import fr.fms.apitrainings.dao.*;
import fr.fms.apitrainings.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for business logic.
 */
@Service
public class IBusinessImpl implements IBusiness {

    @Autowired()
    TrainingRepository trainingRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    /**
     * Retrieves all trainings.
     *
     * @return a list of all trainings.
     */
    @Override
    public List<Training> getTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Retrieves all trainings by category.
     *
     * @param idCategory the ID of the category.
     * @return a list of trainings in the specified category.
     */
    @Override
    public List<Training> getTrainingsByCategory(Long idCategory) {
        Category category = categoryRepository.findById(idCategory).orElse(null);
        return trainingRepository.findByCategory(category);
    }

    /**
     * Retrieves a training by its ID.
     *
     * @param idTraining the ID of the training.
     * @return an Optional containing the training if found, or empty if not found.
     */
    @Override
    public Optional<Training> getTrainingById(Long idTraining) {
        return trainingRepository.findById(idTraining);
    }

    /**
     * Saves a training.
     *
     * @param training the training to save.
     * @return the saved training.
     */
    @Override
    public Training saveTraining(Training training) {
        return trainingRepository.save(training);
    }

    /**
     * Deletes a training by its ID.
     *
     * @param idTraining the ID of the training to delete.
     */
    @Override
    public void deleteTraining(Long idTraining) {
        trainingRepository.deleteById(idTraining);
    }

    /**
     * Retrieves all categories.
     *
     * @return a list of all categories.
     */
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Saves a customer.
     *
     * @param customer the customer to save.
     * @return the saved customer.
     */
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Retrieves a customer by its ID.
     *
     * @param idCustomer the ID of the customer.
     * @return an Optional containing the customer if found, or empty if not found.
     */
    @Override
    public Optional<Customer> getCustomerById(Long idCustomer) {
        return customerRepository.findById(idCustomer);
    }

    /**
     * Saves an order.
     *
     * @param order the order to save.
     * @return the saved order.
     */
    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Saves an order item.
     *
     * @param orderItem the order item to save.
     * @return the saved order item.
     */
    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}

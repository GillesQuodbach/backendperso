package fr.fms.apitrainings.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Represents an item within an order.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@ToString
public class OrderItem implements Serializable {

    /**
     * The unique identifier for the order item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The quantity of the item.
     */
    private int quantity;

    /**
     * The price of the item.
     */
    private double price;

    /**
     * The order associated with the item.
     */
    @ManyToOne
    Order order;

    /**
     * The training associated with the item.
     */
    @ManyToOne
    Training training;
}

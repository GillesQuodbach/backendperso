package fr.fms.apitrainings.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Represents an order entity.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "t_order")
public class Order implements Serializable {

    /**
     * The unique identifier for the order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The amount of the order.
     */
    private double amount;

    /**
     * The customer associated with the order.
     */
    @ManyToOne
    Customer customer;
}

package fr.fms.apitrainings.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Represents a training entity.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Training implements Serializable {

    /**
     * The unique identifier for the training.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the training.
     */
    private String name;

    /**
     * The description of the training.
     */
    private String description;

    /**
     * The price of the training.
     */
    private double price;

    /**
     * The quantity of the training.
     */
    private int quantity;

    /**
     * The image URL of the training.
     */
    private String img;

    /**
     * The category associated with the training.
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

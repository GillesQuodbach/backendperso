package fr.fms.apitrainings.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Represents a customer entity.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Customer implements Serializable {

    /**
     * The unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the customer.
     */
    private String name;

    /**
     * The last name of the customer.
     */
    private String lastname;

    /**
     * The address of the customer.
     */
    private String adress;

    /**
     * The phone number of the customer.
     */
    private String phone;

    /**
     * The email address of the customer.
     */
    private String email;

    /**
     * The list of orders associated with the customer.
     */
    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    @JsonIgnore
    private List<Order> orders;
}

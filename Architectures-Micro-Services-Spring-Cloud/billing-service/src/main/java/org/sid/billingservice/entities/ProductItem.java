package org.sid.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.billingservice.models.Product;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double  quantity;

    private Double price;
    private long productID;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // pour eviter la boucle infini
    @ManyToOne
    private Bill bill;

    @Transient
    private Product product;
}

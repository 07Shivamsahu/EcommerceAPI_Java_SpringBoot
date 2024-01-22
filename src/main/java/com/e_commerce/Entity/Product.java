package com.e_commerce.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    private double price;

    private String category;

    private String image;

    @JsonProperty("rating")
    @Embedded
    private Rating rating;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + title + '\'' +
                ", description=" +  description+
                ", price=" + price +
                '}';
    }

}

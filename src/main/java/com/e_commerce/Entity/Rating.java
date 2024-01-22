package com.e_commerce.Entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Rating {
    private double rate;
    private int count;
}

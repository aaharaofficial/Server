package com.atman.aahara.Family;

import com.atman.aahara.Customer.Base.Customer;
import com.atman.aahara.Family.Enum.CusineType;
import com.atman.aahara.Global.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@Entity
@Table(name = "family")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Family extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "family")
    @JsonManagedReference
    private Set<Customer> customers;

    private Integer servings;

    private CusineType cusineType;

    @OneToOne
    @JoinColumn(name = "creator_id")
    private Customer creator;

    @PrePersist
    @PreUpdate
    public void calculateServings() {
        if (customers != null) {
            this.servings = customers.size();
        }
    }
}

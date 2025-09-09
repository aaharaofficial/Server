package com.atman.aahara.Planner.Family;

import com.atman.aahara.Customer.Domain.Customer;
import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Planner.Enum.CusineType;
import com.atman.aahara.Planner.Planner.Planner;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "family")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Family extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private Integer servings;

    @Enumerated(EnumType.STRING)
    private CusineType cusineType;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Customer> customers;


    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Planner> planners;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @JsonBackReference
    private Customer creator;
}

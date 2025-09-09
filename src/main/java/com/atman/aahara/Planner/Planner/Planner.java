package com.atman.aahara.Planner.Planner;


import com.atman.aahara.Planner.Family.Family;
import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Planner.Items.PlannerItem;
import com.atman.aahara.Planner.Meal.PlannerMeal;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Data
@Table(name = "planner")
public class Planner extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "family_id")
    @JsonBackReference
    private Family family;
    private LocalDate date;
    private DayOfWeek day;
    @OneToMany(mappedBy = "planner")
    @JsonManagedReference
    private List<PlannerMeal> meals;
    @OneToMany(mappedBy = "planner")
    @JsonManagedReference
    private List<PlannerItem> items;
    private BigDecimal rawPrice;
    private BigDecimal totalPrice;
}

package com.legot.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "plan_drivers")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanDrivers {
    @Id
    private String planDriversId;
    private int transportationBudget;
    @ManyToOne
    @JoinColumn(name = "user_email")
    private Users user;  // FK
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "plan_id"),
            @JoinColumn(name = "sequence")
    })
    private Plans plan;  // FK(id, sequence)
    @ManyToOne
    @JoinColumn(name = "transportation_id")
    private Transportations transportation;  // FK

}

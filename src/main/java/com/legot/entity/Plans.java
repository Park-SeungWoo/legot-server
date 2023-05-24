package com.legot.entity;

import com.legot.entity.id.PlansId;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "plans")
@IdClass(PlansId.class)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plans {
    @Id
    private BigInteger planId;  // PK
    @Id
    private int sequence;  // PK
    private boolean isLodging;  // bit in sql (default false)
    @Column(nullable = false)
    private Timestamp arrivalTime;
    @Column(nullable = false)
    private Timestamp departureTime;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trips trip;  // FK
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Locations location;  // FK

}

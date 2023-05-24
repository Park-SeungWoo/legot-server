package com.legot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "user_trip_items")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTripItems {
    @Id
    private BigInteger userTripItemId;
    @ManyToOne
    @JoinColumn(name = "user_trip_id")  // if name = reference column name, reference Column name is omissible.
    private UserTrips userTrips;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Items items;
    private String description;

}

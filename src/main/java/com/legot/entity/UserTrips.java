package com.legot.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "user_trips")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTrips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userTripId;  // bigint in sql = long, unsigned bigint = math.bitinteger
    @ManyToOne
    @JoinColumn(name = "user_email")
    private Users user;  // FK
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trips trip;  // FK
}

package com.legot.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "lodgings")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lodgings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lodgingId;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Locations location;  // FK PK
    @Column(nullable = false)
    private String name;
    private String reserveLink;
}

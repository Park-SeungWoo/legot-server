package com.legot.entity;

import com.legot.enums.Theme;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "trips")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trips {
    @Id
    private String tripId;  // PK
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Theme theme;
    private int days;
    private int memberCount;
    @Column(nullable = false)
    private Timestamp startDate;
    @Column(nullable = false)
    private Timestamp endDate;
    private String deepLink;
    private int tripBudget;
}

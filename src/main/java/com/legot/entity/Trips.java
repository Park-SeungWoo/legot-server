package com.legot.entity;

import com.legot.enums.Theme;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private String tripId;  // PK
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
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

package com.legot.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "transportations")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transportations {
    @Id
    private BigInteger transportationId;  // PK
    @Column(nullable = false)
    private String name;
    private String details;
}

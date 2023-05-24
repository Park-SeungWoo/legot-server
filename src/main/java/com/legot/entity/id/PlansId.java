package com.legot.entity.id;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigInteger;

@Data
public class PlansId implements Serializable {
    @Column(name = "plan_id")
    private BigInteger planId;
    @Column(name = "sequence")
    private int sequence;
}

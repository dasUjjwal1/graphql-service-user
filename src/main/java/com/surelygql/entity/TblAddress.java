package com.surelygql.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Table(name = "tbl_address",indexes = @Index(columnList = "userId"))
@Data
public class TblAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    String province;
    String municipality;
    String zip;
    @Column(unique = true)
    BigInteger userId;
}

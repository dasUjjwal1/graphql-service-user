package com.surely.finance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "tbl_user",indexes = @Index(name = "emailMobileIdx", columnList = "email, mobileNumber"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TblUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    BigInteger id;
    String name;
    @Column(unique = true)
    String mobileNumber;
    @Column(unique = true)
    String email;
    String password;
}

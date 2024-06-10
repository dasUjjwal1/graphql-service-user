package com.serviceusergql.repository.user;

import com.serviceusergql.user.entity.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface UserRepository extends JpaRepository<TblUser, BigInteger> {
}

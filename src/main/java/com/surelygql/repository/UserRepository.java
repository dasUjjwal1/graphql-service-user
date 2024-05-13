package com.surelygql.repository;

import com.surelygql.entity.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TblUser, BigInteger> {
    @Query("SELECT tu FROM TblUser tu WHERE tu.email = :email")
    Optional<TblUser> findUserByEmail(String email);
}

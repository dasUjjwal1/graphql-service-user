package com.serviceusergql.repository.agent;

import com.serviceusergql.entity.user.TblAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public interface AgentRepository extends JpaRepository<TblAgent, BigInteger> {
    @Query("SELECT EXISTS(SELECT ta.mobileNumber from TblAgent ta WHERE mobileNumber = :mobileNumber)")
    Boolean findAgentByMobile(String mobileNumber);
}

package com.serviceusergql.repository.agent;

import com.serviceusergql.entity.user.TblAgent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AgentRepository extends JpaRepository<TblAgent, BigInteger> {
}

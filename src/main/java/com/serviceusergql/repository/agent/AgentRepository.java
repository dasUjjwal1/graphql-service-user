package com.serviceusergql.repository.agent;

import com.serviceusergql.entity.user.TblAgent;
import com.serviceusergql.model.AgentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public interface AgentRepository extends JpaRepository<TblAgent, BigInteger> {
    @Query("SELECT EXISTS(SELECT ta.mobileNumber from TblAgent ta WHERE mobileNumber = :mobileNumber)")
    Boolean findAgentByMobile(String mobileNumber);

//    @Query("SELECT cdm.insert_agent_and_user(:agentModel.name, :agentModel.mobileNumber,:agentModel.password)")
//    void createAgent(AgentModel agentModel);
}

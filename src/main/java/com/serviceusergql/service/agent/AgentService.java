package com.serviceusergql.service.agent;

import com.serviceusergql.agent.model.AgentModel;
import com.serviceusergql.exception.AlreadyPresent;
import com.serviceusergql.global.model.MessageResponse;
import com.serviceusergql.global.model.OtpModel;
import com.serviceusergql.user.entity.TblUser;

public interface AgentService {
    Boolean checkAgent(String mobileNumber) throws Exception;
    MessageResponse sendOtp(OtpModel otpModel) throws Exception;
    TblUser registerAgent(AgentModel agentModel) throws AlreadyPresent;
}

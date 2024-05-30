package com.serviceusergql.service.agent;

import com.serviceusergql.model.AgentModel;
import com.serviceusergql.model.OtpModel;
import com.serviceusergql.response.MessageResponse;

public interface AgentService {
    Boolean checkAgent(String mobileNumber) throws Exception;
    MessageResponse sendOtp(OtpModel otpModel) throws Exception;
    MessageResponse registerAgent(AgentModel agentModel) throws Exception;
}

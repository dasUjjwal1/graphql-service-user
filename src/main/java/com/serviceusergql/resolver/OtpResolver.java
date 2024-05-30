package com.serviceusergql.resolver;

import com.serviceusergql.exception.AlreadyPresent;
import com.serviceusergql.model.OtpModel;
import com.serviceusergql.response.MessageResponse;
import com.serviceusergql.service.agent.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class OtpResolver {
    @Autowired
    private AgentService agentService;
    @MutationMapping
    public MessageResponse sendOtp (@Argument OtpModel otpModel) throws Exception {
       Boolean isPresent = agentService.checkAgent(otpModel.getMobileNumber());
        if (isPresent) {
            Map<String,Object> errorObject = new HashMap<>();
            errorObject.put("mobileNumber",otpModel.getMobileNumber());
            throw new AlreadyPresent("User already registered", errorObject);
        }
      return   agentService.sendOtp(otpModel);
    }
}

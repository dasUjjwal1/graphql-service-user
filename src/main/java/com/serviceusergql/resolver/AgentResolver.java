package com.serviceusergql.resolver;

import com.serviceusergql.model.AgentModel;
import com.serviceusergql.response.MessageResponse;
import com.serviceusergql.service.agent.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AgentResolver {
    @Autowired
    private AgentService agentService;
    @MutationMapping
    public MessageResponse registerAgent (@Argument AgentModel agentModel) throws Exception {
       return agentService.registerAgent(agentModel);

    }
}

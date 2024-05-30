package com.serviceusergql.service.agent;

import com.serviceusergql.entity.user.TblAgent;
import com.serviceusergql.model.AgentModel;
import com.serviceusergql.model.OtpModel;
import com.serviceusergql.repository.agent.AgentRepository;
import com.serviceusergql.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.Map;


@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepository agentRepository;

    HostAndPort hostAndPort = new HostAndPort("localhost", 6379);
    JedisPool jedis = new JedisPool(hostAndPort,
            DefaultJedisClientConfig.builder()
                    .socketTimeoutMillis(5000)  // set timeout to 5 seconds
                    .connectionTimeoutMillis(5000) // set connection timeout to 5 seconds
                    .build());

    @Override
    public Boolean checkAgent(String mobileNumber) {
        return agentRepository.findAgentByMobile(mobileNumber);
    }

    @Override
    public MessageResponse sendOtp(OtpModel otpModel) {
        try (Jedis pool = jedis.getResource()) {
            Map<String, String> stream = new HashMap<>();
            stream.put("email", otpModel.getMobileNumber());
            stream.put("type", otpModel.getOtpType().name());
            pool.xadd("stream1", StreamEntryID.NEW_ENTRY, stream);
            return MessageResponse.builder().message("Otp sent successfully").build();
        }
    }

    @Override
    public MessageResponse registerAgent(AgentModel agentModel) throws Exception {
        TblAgent tblAgent = TblAgent.builder()
                .name(agentModel.getName())
                .mobileNumber(agentModel.getMobileNumber())
                .build();
        agentRepository.save(tblAgent);
        return MessageResponse.builder().message("Agent register").build();
    }
}

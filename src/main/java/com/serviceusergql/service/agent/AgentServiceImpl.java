package com.serviceusergql.service.agent;

import com.serviceusergql.agent.entity.TblAgent;
import com.serviceusergql.agent.model.AgentModel;
import com.serviceusergql.exception.AlreadyPresent;
import com.serviceusergql.global.model.MessageResponse;
import com.serviceusergql.global.model.OtpModel;
import com.serviceusergql.repository.agent.AgentRepository;
import com.serviceusergql.repository.user.UserRepository;
import com.serviceusergql.user.entity.TblUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.Map;


@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private UserRepository userRepository;

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
    public TblUser registerAgent(AgentModel agentModel) throws AlreadyPresent {

        TblAgent agent = agentRepository.save(TblAgent.builder().build());
        TblUser user = TblUser.builder()
                .agentId(agent.getId())
                .name(agent.getName())
                .mobile(agent.getMobileNumber())
                .password(agentModel.getPassword()).build();
        return userRepository.save(user);

    }
}

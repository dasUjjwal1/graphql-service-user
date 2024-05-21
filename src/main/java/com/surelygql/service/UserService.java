package com.surelygql.service;

import com.surelygql.entity.TblUser;
import com.surelygql.exception.AlreadyPresent;
import com.surelygql.model.OtpModel;
import com.surelygql.model.UserModel;
import com.surelygql.repository.UserRepository;
import com.surelygql.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.StreamEntryID;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    Logger Log = LoggerFactory.getLogger(UserService.class);
    HostAndPort hostAndPort = new HostAndPort("localhost", 6379);
    JedisPooled jedis = new JedisPooled(hostAndPort,
            DefaultJedisClientConfig.builder()
                    .socketTimeoutMillis(5000)  // set timeout to 5 seconds
                    .connectionTimeoutMillis(5000) // set connection timeout to 5 seconds
                    .build());

    public Boolean checkUser(String email) throws RuntimeException {
        Optional<TblUser> findUser = userRepository.findUserByEmail(email);
        return findUser.isPresent();
    }

    public MessageResponse sendOtp(OtpModel otpModel) {
        Log.info("otp type",otpModel);
        Map<String, String> stream = new HashMap<>();
        stream.put("email", otpModel.getEmail());
        stream.put("type", otpModel.getOtpType().name());
        jedis.xadd("stream1", StreamEntryID.NEW_ENTRY, stream);
        return MessageResponse.builder().message("Otp sent successfully").build();
    }

    public Boolean verifyOtp(String email, String type) {
        return true;
    }

    public TblUser createUser(UserModel userModel) throws AlreadyPresent {
        TblUser user = TblUser.builder()
                .email(userModel.getEmail())
                .mobileNumber(userModel.getMobileNumber())
                .name(userModel.getName())
                .password(new BCryptPasswordEncoder().encode(userModel.getPassword()))
                .build();
        return userRepository.save(user);
    }
}

package com.surelygql.service;

import com.surelygql.entity.TblUser;
import com.surelygql.exception.AlreadyPresent;
import com.surelygql.exception.NotFound;
import com.surelygql.model.UserModel;
import com.surelygql.repository.UserRepository;
import com.surelygql.response.MessageResponse;
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
    HostAndPort hostAndPort = new HostAndPort("localhost", 6379);
    JedisPooled jedis = new JedisPooled(hostAndPort,
            DefaultJedisClientConfig.builder()
                    .socketTimeoutMillis(5000)  // set timeout to 5 seconds
                    .connectionTimeoutMillis(5000) // set connection timeout to 5 seconds
                    .build());

    public TblUser createUser(UserModel userModel) throws AlreadyPresent {
        Optional<TblUser> findUser = userRepository.findUserByEmail(userModel.getEmail());
        if (findUser.isPresent()) {
            Map<String, Object> params = new HashMap<>();
            params.put("email", userModel.getEmail());
            throw new AlreadyPresent("Failed to add user. user with email already present.", params);
        }
        TblUser user = TblUser.builder()
                .email(userModel.getEmail())
                .mobileNumber(userModel.getMobileNumber())
                .name(userModel.getName())
                .password(new BCryptPasswordEncoder().encode(userModel.getPassword()))
                .build();
        return userRepository.save(user);
    }

    public MessageResponse resetPasswordRequest(String email) throws NotFound {
        Optional<TblUser> findUser = userRepository.findUserByEmail(email);
        if (findUser.isEmpty()) {
            throw new NotFound("Email not registered");
        }
        Map<String, String> stream = new HashMap<>();
        stream.put("email", email);
        jedis.xadd("OTP-" + email, (StreamEntryID) null, stream);
        return MessageResponse.builder().message("Otp sent successfully").build();
    }
}

package com.surely.finance.service;

import com.surely.finance.entity.TblUser;
import com.surely.finance.exception.AlreadyPresent;
import com.surely.finance.model.UserModel;
import com.surely.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public TblUser createUser(UserModel userModel) throws AlreadyPresent {
        Optional<TblUser> findUser = userRepository.findUserByEmail(userModel.getEmail());
        if (findUser.isPresent()) {
            Map<String,Object> params = new HashMap<>();
            params.put("email",userModel.getEmail());
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


}

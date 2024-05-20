package com.surelygql.resolver;

import com.surelygql.exception.AlreadyPresent;
import com.surelygql.model.OtpModel;
import com.surelygql.response.MessageResponse;
import com.surelygql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserMutation {
    @Autowired
    private UserService userService;
    String s = "477571947534";
    String b = "J33AZ8LQBAZKULTHHP7UAC8Q";

    @MutationMapping
    public MessageResponse sendOtp(@Argument OtpModel otpModel) throws AlreadyPresent {
        Boolean isUSerAvailable = userService.checkUser(otpModel.getEmail());
        if (isUSerAvailable) {
            Map<String, Object> user = new HashMap<>();
            user.put("email", otpModel.getEmail());
            throw new AlreadyPresent("User already registered", user);
        }
        return userService.sendOtp(otpModel);
    }

}

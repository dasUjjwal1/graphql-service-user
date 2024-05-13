package com.surelygql.resolver;

import com.surelygql.entity.TblUser;
import com.surelygql.exception.AlreadyPresent;
import com.surelygql.exception.NotFound;
import com.surelygql.model.UserModel;
import com.surelygql.response.MessageResponse;
import com.surelygql.response.UserResponse;
import com.surelygql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserMutation {
    @Autowired
    private UserService userService;

    @MutationMapping
    public UserResponse addUser(@Argument UserModel userModel) throws AlreadyPresent {
        TblUser user = userService.createUser(userModel);
        return UserResponse.builder()
                .id(user.getId())
                .address(null)
                .name(user.getName())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber()).build();
    }
    @QueryMapping
    public MessageResponse resetPasswordRequest(@Argument String email) throws NotFound {
        return userService.resetPasswordRequest(email);
    }

}

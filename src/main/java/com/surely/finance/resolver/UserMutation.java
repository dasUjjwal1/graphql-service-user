package com.surely.finance.resolver;

import com.surely.finance.entity.TblUser;
import com.surely.finance.exception.AlreadyPresent;
import com.surely.finance.model.UserModel;
import com.surely.finance.response.UserResponse;
import com.surely.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

}

package com.surely.finance.response;

import com.surely.finance.entity.TblAddress;
import lombok.Builder;

import java.math.BigInteger;
@Builder
public class UserResponse {
    BigInteger id;
    String name;
    String mobileNumber;
    String userName;
    String email;
    TblAddress address;
}

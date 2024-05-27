package com.serviceusergql.response.agent;

import com.serviceusergql.enums.AgentType;

import java.math.BigInteger;
import java.util.Date;

public class AgentDto {
    private BigInteger id;
    private AgentType agentType;
    private String userName;
    private String name;
    private String mobile;
    private Date dob;
    private String email;
    private String address;
}

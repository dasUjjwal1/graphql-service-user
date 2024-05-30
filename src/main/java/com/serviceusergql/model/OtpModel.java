package com.serviceusergql.model;

import com.serviceusergql.enums.OtpType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtpModel {
    private String mobileNumber;
    @Enumerated(EnumType.STRING)
    private OtpType otpType;
}

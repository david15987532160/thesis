package com.phamanh.thesis.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.catalina.User;
import org.springframework.lang.Nullable;

public class UserModel extends UserModelReq {

    @Builder
    public UserModel(String name, int age) {
        super(name, age);
    }
}

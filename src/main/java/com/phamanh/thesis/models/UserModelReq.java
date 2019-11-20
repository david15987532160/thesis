package com.phamanh.thesis.models;

import lombok.*;

@AllArgsConstructor
public class UserModelReq {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int age;

}

package com.phamanh.thesis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Builder
public class ResultModel {
    @Builder.Default
    private int code = 200;

    @Nullable
    private Object data;

    @Singular
    private List<String> errors;
}

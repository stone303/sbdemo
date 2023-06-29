package com.example.sbdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guocang.shi
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResponse {
    @JsonProperty("status")
    private String code;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("data")
    private String data;
}

package com.example.sbdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author guocang.shi
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResponse {

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("code")
    private String code;

    @JsonProperty("data")
    private String data;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("result")
    private List<String> result;


}

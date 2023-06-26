package com.example.sbdemo.model;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class McdcBody {

    @JsonProperty("requestBody")
    private JSONObject requestBody;

    @JsonProperty("responseBody")
    private JSONObject responseBody;

}

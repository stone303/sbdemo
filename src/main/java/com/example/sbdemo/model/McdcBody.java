package com.example.sbdemo.model;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/***
 * McdcBody定义了一组两两对应的请求值与返回值。
 * McdcResponse定义json数组。
 * @Data ：注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法。
 * @AllArgsConstructor ：注在类上，提供类的全参构造
 * @NoArgsConstructor ：注在类上，提供类的无参构造
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class McdcBody {

    @JsonProperty("requestBody")
    private JSONObject requestBody;

    @JsonProperty("responseBody")
    private JSONObject responseBody;

}

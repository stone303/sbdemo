package com.example.sbdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/***
 * McdcBody定义了一组两两对应的请求值与返回值。
 * McdcResponse定义json数组。
 * @Data ：注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法。
 * @AllArgsConstructor ：注在类上，提供类的全参构造
 * @NoArgsConstructor ：注在类上，提供类的无参构造
 * 上面三个注解来自lombok
 * lombok 是一个工具类库，可以用简单的注解形式来简化代码，提高开发效率。但弊端是大大降低了源代码的可读性和完整性，
 * 降低了阅读源代码的舒适度，所以不建议初学者使用，初学者还是老老实实的手动实现以上功能。
 * @JsonProperty：注在属性上，作用是把该属性的名称序列化为另外一个名称。
 * 例如@JsonProperty(“status”)序列化为json时，“status”作为key, {“stutus”: “success”}
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class McdcResponse {

    @JsonProperty("status")
    private String result;

    @JsonProperty("mcdcResult")
    private List<McdcBody> mcdcResult;

}

package com.example.sbdemo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.sbdemo.model.McdcBody;
import com.example.sbdemo.model.McdcResponse;
import com.example.sbdemo.service.HttpService;
import com.example.sbdemo.service.McdcService;
import com.example.sbdemo.utils.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class McdcController {
    @Autowired
    McdcService mcdcService;
    @Autowired
    HttpService httpService;

    /***
     *接收三个参数:urT 被测APT的服务地址method post、get中的一种被测API的基本请求传参initialParam
     * 将initialParam转换成MC/DC用例组* 遍历MC/DC用例组: 请求被测API (url、method、MC/DC param.
     * 整合得到一组MC/DC param、对应的被测API返回值为json数组
     ** 输出json数组
     *
     */

    @RequestMapping(value = "/mcdc",method = RequestMethod.POST)
    @ResponseBody
    public McdcResponse mcdcResponse(
        @RequestParam(value = "url", required = true) String url,
        @RequestParam(value = "method", required = true) String method,
        @RequestParam(value = "initialParam",required = true) String initialParam){
        //将接收到的initialParam转换为MultivalueMap类型，并生成MC/DC用例组
        log.info("Request: ---"+ url);
        log.info("Request: ---"+ method);
        log.info("Request: ---"+ initialParam);

        // 将String initialParam 转换为MultiValueMap
        MultiValueMap<String, String> initialMultiMap = MapUtil.jsonStr2MultiMap(initialParam);

        // 将MultiValueMap 转换为 List
        List<MultiValueMap<String, String>> mcdcList = McdcService.getMcdcParams(initialMultiMap);

        McdcResponse mcdcResponse = new McdcResponse();
        ResponseEntity responseEntity;
        List<McdcBody> responseList = new ArrayList();
        // 区分get/post请求
        if (method.equals("get")) {

            log.info("Request: ---get---");
            //请求被测API// 遍历MC/DC用例组，
            for (MultiValueMap requestMap : mcdcList) {

                McdcBody mcdcBody = new McdcBody();
                //requestMap 转为String
               //responseEntity = httpService.get(url, MapUtil.map2UrlStr(requestMap));
                log.info("Request: ---get---"+requestMap);
                responseEntity = httpService.get(url, MapUtil.multiValueMapToString(requestMap));

               // 将被测MC/DC用例、对应的被测API返回值装进 MultiValueMap 转 Map
                mcdcBody.setRequestBody(JSONObject.parseObject(JSON.toJSONString(MapUtil.multiValueMap2Map (requestMap))));
                mcdcBody.setResponseBody(JSONObject.parseObject(responseEntity.getBody().toString()));
                responseList.add(mcdcBody);
            }
        }
        else if (method.equals("post")){
            log.info("Request: ---post---");

            // 遍历MC/DC用例组，请求被测API
            for (MultiValueMap requestMap : mcdcList) {
                McdcBody mcdcBody = new McdcBody();
                responseEntity = httpService.post(url, requestMap);
                // 将被测MC/DC用例，对应的被测API返回值装进 McdcBody
                mcdcBody. setRequestBody(JSONObject.parseObject(JSON.toJSONString(MapUtil.multiMap2Map(requestMap))));
                mcdcBody. setResponseBody(JSONObject.parseObject( responseEntity.getBody().toString()));
                responseList.add(mcdcBody) ;
            }
        }
        // McdcBody 装进 McdcResponse
        mcdcResponse.setResult("success");
        mcdcResponse.setMcdcResult(responseList);

        return mcdcResponse;
        }

}

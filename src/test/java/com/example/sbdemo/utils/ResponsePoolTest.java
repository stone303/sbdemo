package com.example.sbdemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ResponsePoolTest {

    String testparam="param";
    HttpClientUtils httpClientUtils = new HttpClientUtils();

    @Test
    public void testVideoPointConfig() {
        String url="http://10.171.197.10:30365/api/wpms-service/videoPointConfig/queryPage";
        String param="{\"limit\":10,\"pageNo\":1,\"total\":70,\"pointName\":\"\"}";
        try {
            JSONObject response = httpClientUtils.sendPostRequest(url, JSON.parseObject(param));

            if (response.get("code") == "0")
            {
                JSONArray list = response.getJSONObject("data").getJSONArray("list");
                JSONObject firstElement = list.getJSONObject(0);

                ResponsePool.getInstance().addResponse("param", firstElement);
            }
            else
            {
                System.out.println(response.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
       /*** JSONObject jsonObject = JSON.parseObject(reposen);
        JSONPath jsonPath = new JSONPath(reposen);
        ResponsePool.getInstance().addResponse("param",  jsonPath.eval("$.data.list[0]"));
        jsonPath.eval 入参需是json对象
        ***/

        /***
        System.out.println("---返回---"+response+"-----");
        string 转json
        JSONObject jsonObject = JSON.parseObject(response);
        JSONArray list = jsonObject.getJSONObject("data").getJSONArray("list");
        JSONObject firstElement = list.getJSONObject(0);

        ResponsePool.getInstance().addResponse("param", firstElement);
         ***/

    }
    @Test
    public void testSaveOrUpdate()
    {
        String url= "http://kong.poros3-platform.10.171.197.10.sslip.io/api/wpms-service/videoRouteConfig/saveOrUpdate";
        JSONObject  jsonObject = new JSONObject();
        jsonObject.put("routeName","test");
        // 构建 videoPointConfigList 数组并将第一个对象添加进去
        JSONArray videoPointConfigList = new JSONArray();
        videoPointConfigList.add(ResponsePool.getInstance().getResponse(testparam));
        System.out.println("-----"+ videoPointConfigList.size());
        jsonObject.put("videoPointConfigList",videoPointConfigList);
        try {
            JSONObject   response = httpClientUtils.sendPostRequest(url,jsonObject );
            System.out.println("-----"+response.toString()+"-----");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Ignore
    @Test
    public void testAddResponse()
    {
        String response="{\n" +
                "    \"code\":0,\n" +
                "    \"msg\":\"success\",\n" +
                "    \"data\":{\n" +
                "        \"total\":70,\n" +
                "        \"list\":[\n" +
                "            {\n" +
                "                \"id\":\"1646425424286216193\",\n" +
                "                \"tenantId\":\"geek\",\n" +
                "                \"createBy\":null,\n" +
                "                \"createByName\":\"admin\",\n" +
                "                \"createTime\":\"2023-04-13 16:09:45\",\n" +
                "                \"updateBy\":null,\n" +
                "                \"updateByName\":\"admin\",\n" +
                "                \"updateTime\":\"2023-04-13 16:09:45\",\n" +
                "                \"pointName\":\"燃气间-能源站西南角朝东\",\n" +
                "                \"monitorPoint\":\"854f6470e19f4e24948a6ab33ae4e7eb\",\n" +
                "                \"monitorPointName\":\"能源站西南角朝东\",\n" +
                "                \"pointPhoto\":\"\",\n" +
                "                \"remark\":null,\n" +
                "                \"pointStatus\":null,\n" +
                "                \"routeId\":null,\n" +
                "                \"modifyStatus\":null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":\"1646421830331740161\",\n" +
                "                \"tenantId\":\"geek\",\n" +
                "                \"createBy\":null,\n" +
                "                \"createByName\":\"admin\",\n" +
                "                \"createTime\":\"2023-04-13 15:55:28\",\n" +
                "                \"updateBy\":null,\n" +
                "                \"updateByName\":\"admin\",\n" +
                "                \"updateTime\":\"2023-04-13 15:55:28\",\n" +
                "                \"pointName\":\"铧润泽纯水中控室-辅助厂房二层纯水间\",\n" +
                "                \"monitorPoint\":\"3afd1fc95108467d873f535e1fe6f1b1\",\n" +
                "                \"monitorPointName\":\"辅助厂房二层纯水间\",\n" +
                "                \"pointPhoto\":\"\",\n" +
                "                \"remark\":null,\n" +
                "                \"pointStatus\":null,\n" +
                "                \"routeId\":null,\n" +
                "                \"modifyStatus\":null\n" +
                "            }\n" +
                "        ],\n" +
                "        \"pageNum\":1,\n" +
                "        \"pageSize\":10,\n" +
                "        \"size\":10,\n" +
                "        \"startRow\":1,\n" +
                "        \"endRow\":10,\n" +
                "        \"pages\":7,\n" +
                "        \"prePage\":0,\n" +
                "        \"nextPage\":2,\n" +
                "        \"isFirstPage\":true,\n" +
                "        \"isLastPage\":false,\n" +
                "        \"hasPreviousPage\":false,\n" +
                "        \"hasNextPage\":true,\n" +
                "        \"navigatePages\":8,\n" +
                "        \"navigatepageNums\":[\n" +
                "            1,\n" +
                "            2,\n" +
                "            3,\n" +
                "            4,\n" +
                "            5,\n" +
                "            6,\n" +
                "            7\n" +
                "        ],\n" +
                "        \"navigateFirstPage\":1,\n" +
                "        \"navigateLastPage\":7\n" +
                "    },\n" +
                "    \"success\":true\n" +
                "}";

        JSONObject jsonObject = JSON.parseObject(response);
        JSONArray list = jsonObject.getJSONObject("data").getJSONArray("list");
        JSONObject firstElement = list.getJSONObject(0);

        System.out.println("---"+firstElement.toString()+"----");
        ResponsePool.getInstance().addResponse(testparam, firstElement);

        System.out.println("---1"+ResponsePool.getInstance().getResponse(testparam).toString()+"----");

    }
    @Ignore
    @Test
    public void testAddResponse2()
    {
        System.out.println("---2"+ResponsePool.getInstance().getResponse(testparam).toString()+"----");

    }
}
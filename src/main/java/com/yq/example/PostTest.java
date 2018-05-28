package com.yq.example;

import com.wxt.util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;
//对接口进行测试
public class PostTest {
    private String url = "https://192.168.1.101/";
    private String charset = "utf-8";
    private HttpClientUtil httpClientUtil = null;

    public PostTest() {
        httpClientUtil = new HttpClientUtil();
    }

    public void test() {
        String httpOrgCreateTest = url + "httpOrg/create";
        Map<String, String> createMap = new HashMap<String, String>();
        createMap.put("authuser", "*****");
        createMap.put("authpass", "*****");
        createMap.put("orgkey", "****");
        createMap.put("orgname", "****");
        String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest, createMap, charset);
        System.out.println("result:" + httpOrgCreateTestRtn);
    }

    public static void main(String[] args) {
        PostTest main = new PostTest();
        main.test();
    }
}
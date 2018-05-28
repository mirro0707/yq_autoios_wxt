package com.yq.example;

import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


public class FluentRequestsTest {
    public static void main(String[] args) throws IOException {
        //执行一个GET请求,同时设置Timeout参数并将响应内容作为String返回

        CookieStore cookieStore = new BasicCookieStore();

        Executor executor = Executor.newInstance();
        String resultString = executor.use(cookieStore)
                .execute(Request.Get("http://10.155.20.160:8080/wxApp/a/appFeedback/feedBackList")
                        .addHeader("Cookie","JSESSIONID=97BB1A248391D490AB572C67B542C700")
                        .connectTimeout(1000)
                        .socketTimeout(1000))
                .returnContent().asString();

        System.out.println(resultString);

    }

    @Test
    public void test1() throws IOException {
        //执行一个GET请求,同时设置Timeout参数并将响应内容作为String返回

        CookieStore cookieStore = new BasicCookieStore();

        Executor executor = Executor.newInstance();
        String resultString = executor.use(cookieStore)
                .execute(Request.Get("http://10.155.20.160:8080/wxApp/a/appFeedback/feedBackList")
                        .addHeader("Cookie","JSESSIONID=97BB1A248391D490AB572C67B542C700; Hm_lvt_82116c626a8d504a5c0675073362ef6f=1509678800; Hm_lpvt_82116c626a8d504a5c0675073362ef6f=1509693850")
                        .connectTimeout(1000)
                        .socketTimeout(1000))
                .returnContent().asString();

        System.out.println(resultString);

    }
    @Test
    public void test2() throws IOException {
        //执行一个GET请求,同时设置Timeout参数并将响应内容作为String返回
        Request.Get("http://blog.csdn.net/vector_yi")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asString();

        //以Http/1.1版本协议执行一个POST请求，同时配置Expect-continue handshake达到性能调优,
        //请求中包含String类型的请求体并将响应内容作为byte[]返回
        Request.Post("http://blog.csdn.net/vector_yi")
                .useExpectContinue()
                .version(HttpVersion.HTTP_1_1)
                .bodyString("Important stuff", ContentType.DEFAULT_TEXT)
                .execute().returnContent().asBytes();


        //通过代理执行一个POST请求并添加一个自定义的头部属性,请求包含一个HTML表单类型的请求体
        //将返回的响应内容存入文件
        Request.Post("http://blog.csdn.net/vector_yi")
                .addHeader("X-Custom-header", "stuff")
                .viaProxy(new HttpHost("myproxy", 8080))
                .bodyForm(Form.form().add("username", "vip").add("password", "secret").build())
                .execute().saveContent(new File("result.dump"));
    }

}

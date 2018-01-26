package com.aliyun.iotx.api.client;
/*
* Copyright 2017 Alibaba Group
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;

/**
 * @author zhongfu.xiezf
 */
public class RequestDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        SyncApiClient syncClient = SyncApiClient.newBuilder()
            .appKey("xxxxxxx")
            .appSecret("xxxxxxxxxx")
            .build();

        IoTApiRequest request = new IoTApiRequest();
        //设置api的版本
        request.setApiVer("1.0.0");
        request.setId("42423423");

        //如果需要登陆，设置当前的会话的token
        request.setIoTToken("xxxxxxxxxxxxxxx");

        //设置参数
        request.putParam("xxxx", "xxxxx");

        //请求参数域名、path、request
        String host = "xxx.xxx.xxx:8080";
        String path = "/xxxx/xxxx/xxxx";
        ApiResponse response = syncClient.postBody(host, path, request);

        System.out.println(
            "response code = " + response.getStatusCode() + " response content = " + new String(response.getBody(),
                "utf-8"));
    }
}


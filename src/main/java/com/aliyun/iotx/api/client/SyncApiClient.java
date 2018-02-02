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
import java.util.Map;

import com.alibaba.cloudapi.sdk.core.BaseApiClient;
import com.alibaba.cloudapi.sdk.core.BaseApiClientBuilder;
import com.alibaba.cloudapi.sdk.core.annotation.NotThreadSafe;
import com.alibaba.cloudapi.sdk.core.annotation.ThreadSafe;
import com.alibaba.cloudapi.sdk.core.enums.Method;
import com.alibaba.cloudapi.sdk.core.enums.Scheme;
import com.alibaba.cloudapi.sdk.core.model.ApiRequest;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.cloudapi.sdk.core.model.BuilderParams;
import com.alibaba.fastjson.JSONObject;

/**
 * @author zhongfu.xiezf
 */
@ThreadSafe
public final class SyncApiClient extends BaseApiClient {

    private SyncApiClient(BuilderParams builderParams) {
        super(builderParams);
    }

    @NotThreadSafe
    public static class Builder extends BaseApiClientBuilder<Builder, SyncApiClient> {

        @Override
        protected SyncApiClient build(BuilderParams params) {
            return new SyncApiClient(params);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static SyncApiClient getInstance() {
        return getApiClassInstance(SyncApiClient.class);
    }

    public ApiResponse postBody(String host, String path, IoTApiRequest request, boolean isHttps,
                                Map<String, String> headers)
        throws UnsupportedEncodingException {
        byte[] body = JSONObject.toJSONString(request).getBytes("UTF-8");
        ApiRequest apiRequest = new ApiRequest(isHttps ? Scheme.HTTPS : Scheme.HTTP, Method.POST_BODY, host, path,
            body);
        if (null != headers && headers.size() > 0) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                apiRequest.getHeaders().put(header.getKey(), header.getValue());
            }
        }
        return syncInvoke(apiRequest);
    }

    public ApiResponse postBody(String host, String path, IoTApiRequest request, boolean isHttps)
        throws UnsupportedEncodingException {

        return postBody(host, path, request, isHttps, null);
    }

    public ApiResponse postBody(String host, String path, IoTApiRequest request)
        throws UnsupportedEncodingException {
        return postBody(host, path, request, false, null);
    }

}


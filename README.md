
# 介绍
阿里云IoT网关，旨在通过http/https的协议将物联网通用套件及衍生服务开放给开放者。本文档主要介绍如何访问阿里云IoT网关。IoT网关依赖阿里云API网关，IoT网关在阿里云API网关的基础上扩展了IoT的特殊协议。

# 协议框架

```
    {
        "id": "1509086454180",
        "version": "1.0",
        "request": { },
        "params": { }
    }
```

**id**: 一次请求的标示，用于请求跟踪问题排查，通常用UUID生成
**version**: 协议版本，当前版本1.0
**request**: JSON对象，与业务无关的通用参数，包括如下可用参数
| 参数 	| 是否必填 	| 参数描述 	|
|:---|:---|:---|
| apiVer 	| 是 	| API版本 	|
| iotToken 	| 否 	| 登录用户的token（客户端） 	|
| cloudToken 	| 否 	| 云端资源token（云端） 	|
| language 	| 否 	| 国际化扩展，语言。 	|
| locale 	| 否 	| 国际化扩展，地理位置、ip。 	|

**params**: JSON对象，访问指定api的业务参数
# 示例
假设您需要访问IoT网关提供的如下API：
**定义描述**
| path 	| 版本 	| 描述 	| 是否需要用户身份的鉴权|
|:---|:---|:---|:---|
|/awss/enrollee/list/get|1.0.2|分页查询发现设备列表，用于配网流程|是，客户端SDK需启用身份的鉴权|
**请求参数**
| 参数 	| 类型 | 是否必填 	| 参数描述 	|
|:---|:---|:---|:---|
|pageSize|Integer|是|分页大小|
|pageNum|Integer|是|页编号|

**请求示例**
版本1.0.2对应request中的apiVer，由于API要求用户身份验证，request中必须包含参数iotToken。请求参数统一放到params中。

```
{
    "id": "1509086454180",
    "version": "1.0",
    "request": {
        "apiVer": "1.0.2",
        "iotToken": "token"
    },
    "params": {
        "pageSize": 10，
        "pageNum": 1
    }
}
```

假设您已经拥有了访问IoT网关的appKey和appSecret 分别为appkey1和appsecret1，通过IoT网关访问如下：
```
		SyncApiClient syncClient = SyncApiClient.newBuilder()
            .appKey("appkey1")
            .appSecret("appsecret1")
            .build();

        IoTApiRequest request = new IoTApiRequest();
        //设置api的版本
        request.setApiVer("1.0.2");
        request.setId("123456");

        //如果需要登陆，设置当前的会话的token，token通过登录api获取
        request.setIoTToken("token");

        //设置参数
        request.putParam("pageSize", 10);
        request.putParam("pageNum", 1);
        

        //请求参数域名、path
        String host = "api.link.aliyun.com";
        String path = "/awss/enrollee/list/get";
        ApiResponse response = syncClient.postBody(host, path, request);

        System.out.println(
            "response code = " + response.getStatusCode() + " response content = " + new String(response.getBody(),
                "utf-8"));
```

# 依赖
```
<dependency>
    <groupId>com.aliyun.api.gateway</groupId>
    <artifactId>sdk-core-java</artifactId>
    <version>1.0.4</version>
</dependency>
```
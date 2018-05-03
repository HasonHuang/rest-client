# RestClient

基于 SpringBoot 的声明式 Rest 调用框架。

# 说明

框架只演示了get请求，需要支持post请求和其他认证方式的（如sso），自己寻找TODO标签补全代码即可。



# 使用说明

## 指定RestTemplate

使用RestTemplate处理请求。需要配置RestTemplate。SpringBoot下配置如下：

```Java
@Autowired(required = false)
List<ClientHttpRequestInterceptor> interceptors;

@Bean
public RestTemplate restTemplate() {
  System.out.println("-------restTemplate-------");

  RestTemplate restTemplate = new RestTemplate();

  // 设置拦截器，用于http basic的认证等
  restTemplate.setInterceptors(interceptors);

  return restTemplate;
}
```

如果需要登录，以http basic认证为例，增加以下配置bean即可

```Java
@Component
public class HttpBasicRequestInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
      throws IOException {

    // TODO 需要得到当前用户
    System.out.println("---------需要得到当前用户，然后设置账号密码-----------");

    String plainCreds = "hason:admin";
    byte[] plainCredsBytes = plainCreds.getBytes();
    byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);

    String headerValue = new String(base64CredsBytes);

    HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
    requestWrapper.getHeaders().add("Authorization", "Basic " + headerValue);

    return execution.execute(requestWrapper, body);
  }
}
```

## 编写接口类

接口上指定 `@Rest` 配置服务器信息，方法上指定 `@GET` 配置请求信息。

```Java
@Rest("http://localhost:8000")
public interface RequestDemo {

  @GET
  ResultBean get1();

  @GET("/get2")
  ResultBean getWithKey(@Param("key") String key);

  @GET("/get3")
  ResultBean getWithMultKey(@Param("key1") String key, @Param("key2") String key2);
}
```


## 使用

直接在spring框架上注入接口即可使用。

```Java
@Autowired
private RequestDemo demo;

public String test() {
  String msg = "<h1>invoke remote rest result</h1>";

  ResultBean get1 = demo.get1();

  msg += "<br/>get1 result=" + get1;

  ResultBean get2 = demo.getWithKey("key-------");

  msg += "<br/>get2 result=" + get2;

  ResultBean get3 = demo.getWithMultKey("key11111", "key22222");

  msg += "<br/>get3 result=" + get3;

  return msg;
}
```

# 使用本工程例子

导入2个SpringBoot工程 rest-server 和 rest-util。rest-server 端口为8000，用于测试rest服务。 

1. 启动 `rest-server`
2. 运行 `rest-util` 中的单元测试 `DemoTest`

# 工作原理

框架代码在单独的 `rest-client\rest-util` 目录中，主要逻辑都在 `RestClientPostProcessor` 上，代码非常精简，一看就明白。

* 使用 `org.reflections.Reflections` 得到所有配置了 `@Rest` 的接口列表
* 根据 `@Rest` 得到服务器配置信息 `RestInfo`
* 使用 `Proxy.newProxyInstance` 生成接口的代理类，`invoke` 方法中根据 `@GET` 得到该方法请求信息 `RequestInfo`，调用 `IRequestHandle` 接口处理请求，。
* 把生成的代理类注入到spring容器中。

# 如果不使用RestTemplate需要怎么修改？

实现 `RequestHandler` , 注释掉默认的 `RestTemplateRequestHandler` 类即可。
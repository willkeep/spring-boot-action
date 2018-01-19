# spring-boot-swagger 总结
## swagger maven依赖
```
        <!-- swagger2 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.6.1</version>
        </dependency>

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.6.1</version>
        </dependency>
```
## swagger全局配置
```
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //设置组名，swagger-ui.html 右上角下拉框选择
                .groupName("RestfulApi")
                //是否使用默认的返回状态（http状态码）
                .useDefaultResponseMessages(true)
                //自定义返回状态码
                .globalResponseMessage(RequestMethod.POST,getResponseMessageList())
                .apiInfo(apiInfo())
                //restful选择器
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.willkeep.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 统一设置返回状态码和含义
     * 当具体接口设置了返回码时，设置冲突时，以接口设置为准
     * @return
     */
    List<ResponseMessage> getResponseMessageList(){
        List<ResponseMessage> list = Lists.newArrayList();
        list.add(new ResponseMessageBuilder().code(-1).message("用户不存在").responseModel(new ModelRef("用户不存在")).build());
        list.add(new ResponseMessageBuilder().code(-2).message("用户已冻结").responseModel(new ModelRef("用户已冻结")).build());
        return list;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .version("1.0")
                .build();
    }
}
```
## 接口配置
```
    /**
     * 保存用户信息
     */
    @ApiOperation(value = "保存用户信息")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = -3, message = "-3"),
            @ApiResponse(code = -2, message = "-2")}
    )
    @PostMapping("save")
    public FrontResult<String> saveInfo(@RequestBody @ApiParam(name = "用户信息",value = "json格式",required = true) User user) {
        log.info("user:{}", JsonMapper.INSTANCE.toJson(user));
        return FrontResult.newSuccess();
    }
```
## 文档和测试
1. 启动spring-boot
使用maven插件启动
![image_1c47hcnp5gl91q1rh0u1e67a5c9.png-42.6kB][1]
2. 访问swagger接口文档
```
http://127.0.0.1:8080/swagger-ui.html
```
3. 测试接口
根据描述输入参数，测试接口
![image_1c47hhph11isd10q61h0j1n5k12dnm.png-75.1kB][2]

  [1]: http://static.zybuluo.com/swellwu/d5e5qbnpllp2h2cclh3sabdf/image_1c47hcnp5gl91q1rh0u1e67a5c9.png
  [2]: http://static.zybuluo.com/swellwu/2wgwigk0kezr0t6qbnk5zuec/image_1c47hhph11isd10q61h0j1n5k12dnm.png
  [3]: http://static.zybuluo.com/swellwu/imltdjspiab44xui8j690pkx/image_1c47hjqaj7ubv8119d1adp1iqo13.png
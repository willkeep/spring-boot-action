package com.willkeep.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2018-01-19
 */
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

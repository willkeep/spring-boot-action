package com.willkeep.controller;

import com.willkeep.po.User;
import com.willkeep.util.FrontResult;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springside.modules.utils.mapper.JsonMapper;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2018-01-19
 */
@Slf4j
@Api(description = "用户接口")
@RestController
@RequestMapping("/user")
public class SwaggerTestController {

    /**
     * 获取用户信息
     */
    @ApiOperation(value = "获取用户信息")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @GetMapping("get")
    public FrontResult<User> getInfo(@RequestParam String uuid) {
        User user = new User(uuid, "张三", 18);
        log.info("user:{}", JsonMapper.INSTANCE.toJson(user));
        return FrontResult.newSuccess(user);
    }

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
}

package com.willkeep.controller;

import com.willkeep.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.utils.time.DateFormatUtil;

import java.util.Date;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2018-01-31
 */
@RestController("/hello")
public class HelloController {

    @Autowired
    private AuthorService authorService;

    @GetMapping({"/","index"})
    public String index() {
        return String.format("hello world! author:%s now:%s",authorService.getAuthor(),
                DateFormatUtil.formatDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, new Date()));
    }
}

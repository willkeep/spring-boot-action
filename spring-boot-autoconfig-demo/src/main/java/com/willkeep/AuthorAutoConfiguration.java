package com.willkeep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2018-01-31
 */
@Configuration
@ConditionalOnClass(AuthorService.class)
@EnableConfigurationProperties(AuthorProperties.class)
public class AuthorAutoConfiguration {

    @Autowired
    private AuthorProperties authorProperties;

    @Bean
    @ConditionalOnMissingBean(AuthorService.class)
    @ConditionalOnProperty(name = "custom.author.enabled", havingValue = "true" ,matchIfMissing = true)
    public AuthorService authorResolver() {
        AuthorService authorService = new AuthorService();
        authorService.setAuthor(authorProperties.getAuthor());
        return authorService;
    }
}

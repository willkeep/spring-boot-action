package com.willkeep;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2018-01-31
 */
@ConfigurationProperties(prefix = "custom")
public class AuthorProperties {

    private String author = "Jack";

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

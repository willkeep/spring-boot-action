package com.willkeep.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2018-01-19
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String uuid;
    private String name;
    private Integer age;
}

## 自动配置demo
### maven
用于配置属性
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-autoconfigure</artifactId>
        <optional>true</optional>
    </dependency>
```
### 定义config 和 service
根据需要定义

### 定义自动配置规则

条件配置注解说明：
`@ConditionalOnClass(AuthorService.class)` - 当存在AuthorService.class时，配置此类
`@EnableConfigurationProperties(AuthorProperties.class)` - 使用AuthorProperties配置类
`@ConditionalOnMissingBean(AuthorService.class)` - 当不存在此类bean时，初始化此代码（用户优先，如果用户自己创建了bean，则不自动配置）
`@ConditionalOnProperty(name = "custom.author.enabled", matchIfMissing = true)` - 如果存在custom.author.enabled=true则开启，不存在也开启
`@ConditionalOnWebApplication` - 是web应用开启
```java
    @Configuration
    @ConditionalOnClass(AuthorService.class)
    @EnableConfigurationProperties(AuthorProperties.class)
    public class AuthorAutoConfiguration {
    
        @Autowired
        private AuthorProperties authorProperties;
    
        @Bean
        @ConditionalOnMissingBean(AuthorService.class)
        @ConditionalOnProperty(name = "custom.author.enabled", matchIfMissing = true)
        public AuthorService authorResolver() {
            AuthorService authorService = new AuthorService();
            authorService.setAuthor(authorProperties.getAuthor());
            return authorService;
        }
    }
```

### 开启自定义自动配置
在 `classpath:META-INF` 下创建`spring.factories`，添加自动配置类
```properties
    # CUSTOM
    org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
    com.willkeep.AuthorAutoConfiguration
```

## 使用
依赖mavne：
```xml
    <!-- 自动配置demo -->
    <dependency>
        <groupId>spring-boot-action</groupId>
        <artifactId>spring-boot-autoconfig-demo</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```
即可完成自动配置注入：
```java
    @Autowired
    private AuthorService authorService;
```
使用见spring-boot-core下`HelloController::index`
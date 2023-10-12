package com.kuang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
//开启swagger的自动配置
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 配置docket以配置swagger具体参数
     * /**
     * return new Docket(DocumentationType.SWAGGER_2)
     * .apiInfo(apiInfo())
     * .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
     * .apis(RequestHandlerSelectors.basePackage("com.kuang.swagger.controller"))
     * // 配置如何通过path过滤,即这里只扫描请求以/kuang开头的接口
     * .paths(PathSelectors.ant("/kuang/**"))
     * .build();
     * <p>
     * any() // 扫描所有，项目中的所有接口都会被扫描到
     * none() // 不扫描接口
     * // 通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
     * withMethodAnnotation(final Class<? extends Annotation> annotation)
     * // 通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
     * withClassAnnotation(final Class<? extends Annotation> annotation)
     * basePackage(final String basePackage) // 根据包路径扫描接口
     * any() // 任何请求都扫描
     * none() // 任何请求都不扫描
     * regex(final String pathRegex) // 通过正则表达式控制
     * ant(final String antPattern) // 通过ant()控制
     *
     * @return
     */

    @Bean
    public Docket docket(Environment environment) {
//        设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");
//        判断当前是否处于该环境
//        通过 enable() 接受此参数判断是否要显示
        boolean b = environment.acceptsProfiles(of);
        b = true;

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("group12580")
                .apiInfo(apiInfo())
                .enable(b) //配置是否启用swagger，如果是false，浏览器将无法访问
                .select() //通过.select()方法，去配置扫描接口，requestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage(("com.kuang.swagger.controller")))
//                配置如何通过path过滤,即这里只扫描请求以/kuang开头的接口
//                .paths(PathSelectors.ant("com/kuang/**"))
                .build();
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
//                .select(). //通过.select()方法，去配置扫描接口，requestHandlerSelectors配置如何扫描接口
//                apis(RequestHandlerSelectors.any()).build();

    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("梦晴", "www.mq.com", "506753586@qq.com");
//        String title, String description, String version, String termsOfServiceUrl, Contact contact,
//        String license, String licenseUrl, Collection<VendorExtension> vendorExtensions
        return new ApiInfo("Swagger标题",
                "描述-学习swagger啦",
                "1.0",
                "www.group.com",
                contact,
                "Apach 2.0 许可",
                "许可链接",
                new ArrayList<>());
    }
}

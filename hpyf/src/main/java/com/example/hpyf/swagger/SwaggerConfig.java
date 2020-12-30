package com.example.hpyf.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 配置
 *
 * @Author wxy
 * @Date 2020/12/2 15:48
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {

    /**
     * 创建RestApi 并包扫描controller
     */
    @Bean
    @Order(1)
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 将api的元信息设置为包含在json ResourceListing响应中。
                .apiInfo(apiInfo())
                //注意，微服务架构下groupName一定要去掉，不然访问资源会出现404，导致Knife4j文档请求异常
                .select()
                // 选择哪些接口作为swagger的doc发布
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("knife4j-swagger RESTFul APIs")
                .description("knife4j-swagger")
                .termsOfServiceUrl("http://www.doctorlm.com/")
                //contact是一个重载方法，其中一个为contact(String contact),但是不建议使用，建议使用contact(Contact contact)
                .contact(new Contact("医盟", "http://www.doctorlm.com/", "doctorgroup@doctorlm.com"))
                .version("1.0")
                .build();
    }
}

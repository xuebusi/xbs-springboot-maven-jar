package com.xuebusi.springboot.maven.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConf {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        builder.additionalMessageConverters(new FastJsonHttpMessageConverter());
        builder.additionalMessageConverters(new FormHttpMessageConverter());
        builder.setConnectTimeout(30000);
        builder.setReadTimeout(300000);
        return builder.build();
    }
}

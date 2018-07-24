package com.answern.springboot.dubbo.configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.answern.springboot.dubbo.properties.DubboClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sundexu on 2017/9/14.
 */
@Configuration
@EnableConfigurationProperties(DubboClientProperties.class)
public class DubboClientAutoConfiguration {

    @Autowired
    private DubboClientProperties properties;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig config = new ApplicationConfig();
        config.setName(this.properties.getAppName());
        return config;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig config = new RegistryConfig();
        config.setAddress(this.properties.getRegistry());
        config.setGroup(this.properties.getGroup());
        return config;
    }

}

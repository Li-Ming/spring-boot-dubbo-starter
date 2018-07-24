package com.answern.springboot.dubbo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by sundexu on 2017/9/14.
 */
@Data
@ConfigurationProperties(prefix = DubboClientProperties.DUBBO_CLIENT_PREFIX)
public class DubboClientProperties {

    public static final String DUBBO_CLIENT_PREFIX = "dubbo.client";

    private String appName = "Unknow";

    private String registry = "zookeeper://127.0.0.1:2181";

    private String reference;

    private String group;

    private int retries = 0;

    private int timeout = 30000;

    public String getAppName() {
        return appName;
    }

    public String getRegistry() {
        return registry;
    }

    public String getGroup() {
        return group;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}

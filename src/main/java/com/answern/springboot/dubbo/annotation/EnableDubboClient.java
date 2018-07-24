package com.answern.springboot.dubbo.annotation;

import com.answern.springboot.dubbo.registry.DubboClientRegistrar;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

/**
 * Created by sundexu on 2017/9/14.
 * Spring boot style
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(DubboClientRegistrar.class)
public @interface EnableDubboClient {

}

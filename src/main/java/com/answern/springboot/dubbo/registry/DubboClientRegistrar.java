package com.answern.springboot.dubbo.registry;

import com.answern.springboot.dubbo.processor.DubboReferenceProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by sundexu on 2017/9/15.
 */
public class DubboClientRegistrar implements ImportBeanDefinitionRegistrar {

    public static final String BEAN_NAME = "dubboProcessor";

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DubboReferenceProcessor.class);
        registry.registerBeanDefinition(DubboClientRegistrar.BEAN_NAME, beanDefinition);
    }

}

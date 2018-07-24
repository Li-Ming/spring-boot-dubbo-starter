package com.answern.springboot.dubbo.processor;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import java.lang.reflect.Field;

/**
 * Created by sundexu on 2017/9/15.
 */
public class DubboReferenceProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private RegistryConfig registryConfig;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Reference reference = field.getAnnotation(Reference.class);
                if (reference != null) {
                    ReferenceBean referenceBean = this.createReferenceBean(reference);
                    referenceBean.afterPropertiesSet();
                    field.setAccessible(true);
                    field.set(bean, referenceBean.getObject());
                }
            } catch (Throwable e) {
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //TODO nothing to do...
        return bean;
    }

    /**
     * Create ReferenceBean.
     * @param reference A {@link Reference} object.
     * @return ReferenceBean.
     */
    private ReferenceBean createReferenceBean(Reference reference) {
        ReferenceBean referenceBean = new ReferenceBean();
        referenceBean.setInterface(reference.interfaceName());
        referenceBean.setInterface(reference.interfaceClass());
        referenceBean.setGroup(reference.group());
        referenceBean.setRetries(reference.retries());
        referenceBean.setTimeout(reference.timeout());
        referenceBean.setCheck(reference.check());
        referenceBean.setLoadbalance(reference.loadbalance());
        referenceBean.setApplicationContext(this.applicationContext);
        referenceBean.setRegistry(this.registryConfig);
        referenceBean.setApplication(this.applicationConfig);
        return referenceBean;
    }

}

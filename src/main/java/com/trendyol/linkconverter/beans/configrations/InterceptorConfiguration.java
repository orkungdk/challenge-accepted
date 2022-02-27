package com.trendyol.linkconverter.beans.configrations;

import com.trendyol.linkconverter.beans.AuditInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author orkungedik
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuditInterceptor auditInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(auditInterceptor).addPathPatterns("/v1/convert/to-web-url", "/v1/convert/to-deep-link");
    }
}
package com.trendyol.linkconverter.beans.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author orkungedik
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "trendyol.link.web-url.path")
public class WebUrlPathProperties {

    private String productDetailIdentifier;

    private String productDetailPrefix;

    private String searchIdentifier;

}

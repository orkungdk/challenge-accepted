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
@ConfigurationProperties(prefix = "trendyol.link.web-url")
public class WebUrlProperties {

    private String host;

    private String scheme;

    private WebUrlPathProperties path;

}

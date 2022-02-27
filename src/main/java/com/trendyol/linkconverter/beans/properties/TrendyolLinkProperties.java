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
@ConfigurationProperties(prefix = "trendyol.link")
public class TrendyolLinkProperties {

    private WebUrlProperties webUrl;

    private DeepLinkProperties deepLink;

}

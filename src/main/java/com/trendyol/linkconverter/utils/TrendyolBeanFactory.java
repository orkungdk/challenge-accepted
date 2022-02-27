package com.trendyol.linkconverter.utils;

import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.services.impl.DeepLinkToWebUrlConverter;
import com.trendyol.linkconverter.services.impl.WebUrlToDeepLinkConverter;
import com.trendyol.linkconverter.services.proxy.LinkConverter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author orkungedik
 */
@Service
public class TrendyolBeanFactory {

    @Autowired
    private BeanFactory beanFactory;

    public LinkConverter getBean(LinkType linkType) {
        Class converter = linkType.equals(LinkType.WEB_URL)
                ? WebUrlToDeepLinkConverter.class
                : DeepLinkToWebUrlConverter.class;

        return (LinkConverter) beanFactory.getBean(converter);
    }
}

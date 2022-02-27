package com.trendyol.linkconverter.utils;

import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.services.impl.DeepLinkToWebUrlConverter;
import com.trendyol.linkconverter.services.impl.WebUrlToDeepLinkConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.BeanFactory;

/**
 * @author orkungedik
 */
@RunWith(MockitoJUnitRunner.class)
public class TrendyolBeanFactoryTest {

    @Spy
    @InjectMocks
    private TrendyolBeanFactory trendyolBeanFactory;
    @Mock
    private BeanFactory springBeanFactory;
    @Mock
    private WebUrlToDeepLinkConverter webUrlToDeepLinkConverter;
    @Mock
    private DeepLinkToWebUrlConverter deepLinkToWebUrlConverter;

    @Before
    public void setup() {
        Mockito.when(springBeanFactory.getBean(WebUrlToDeepLinkConverter.class))
                .thenReturn(webUrlToDeepLinkConverter);
        Mockito.when(springBeanFactory.getBean(DeepLinkToWebUrlConverter.class))
                .thenReturn(deepLinkToWebUrlConverter);
    }

    @Test
    public void testGetBean() {
        Assert.assertTrue(trendyolBeanFactory.getBean(LinkType.WEB_URL) instanceof WebUrlToDeepLinkConverter);
        Assert.assertTrue(trendyolBeanFactory.getBean(LinkType.DEEP_LINK) instanceof DeepLinkToWebUrlConverter);
    }
}

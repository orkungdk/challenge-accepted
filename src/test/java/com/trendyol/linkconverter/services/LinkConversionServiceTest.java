package com.trendyol.linkconverter.services;

import com.trendyol.linkconverter.api.requests.LinkRequest;
import com.trendyol.linkconverter.beans.LinkConversionSession;
import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.services.impl.LinkConversionServiceImpl;
import com.trendyol.linkconverter.services.proxy.LinkConverter;
import com.trendyol.linkconverter.utils.TrendyolBeanFactory;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URI;

/**
 * @author orkungedik
 */
@RunWith(MockitoJUnitRunner.class)
public class LinkConversionServiceTest {

    private static final String FROM_URL = "ty://Page=Favorites";

    private static final String TO_URL = "https://www.trendyol.com";

    @Spy
    @InjectMocks
    private LinkConversionServiceImpl linkConversionService;
    @Mock
    private LinkConverter linkConverter;
    @Mock
    private TrendyolBeanFactory beanFactory;
    @Mock
    private LinkConversionSession session;

    private LinkRequest linkRequest;


    @SneakyThrows
    @Before
    public void setup() {
        linkRequest = new LinkRequest(FROM_URL);

        Mockito.when(beanFactory.getBean(Mockito.any(LinkType.class)))
                .thenReturn(linkConverter);
        Mockito.when(linkConverter.convert(Mockito.any(URI.class)))
                .thenReturn(new URI(TO_URL));
    }

    @Test
    public void testConvert() {
        var linkResponse = linkConversionService.convert(linkRequest, LinkType.DEEP_LINK);

        Assert.assertEquals(linkResponse.getFrom().getLink().toString(), FROM_URL);
        Assert.assertEquals(linkResponse.getFrom().getType(), LinkType.DEEP_LINK);
        Assert.assertEquals(linkResponse.getTo().getLink().toString(), TO_URL);
        Assert.assertEquals(linkResponse.getTo().getType(), LinkType.WEB_URL);
    }
}

package com.trendyol.linkconverter.services;

import com.trendyol.linkconverter.constants.PageType;
import com.trendyol.linkconverter.constants.QueryParameter;
import com.trendyol.linkconverter.beans.properties.DeepLinkProperties;
import com.trendyol.linkconverter.beans.properties.TrendyolLinkProperties;
import com.trendyol.linkconverter.beans.properties.WebUrlPathProperties;
import com.trendyol.linkconverter.beans.properties.WebUrlProperties;
import com.trendyol.linkconverter.services.impl.WebUrlToDeepLinkConverter;
import com.trendyol.linkconverter.utils.LinkUtils;
import com.trendyol.linkconverter.validations.ValidationFacade;
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
public class WebUrlToDeepLinkConverterTest {
    @Spy
    @InjectMocks
    private WebUrlToDeepLinkConverter webUrlToDeepLinkConverter;
    @Mock
    private ValidationFacade validationFacade;
    @Mock
    private TrendyolLinkProperties properties;

    @Before
    public void setup() {
        initMocks();
        Mockito.doNothing().when(validationFacade).validateWebUrl(Mockito.any(URI.class));
    }

    @SneakyThrows
    @Test
    public void testConvertProductDetail() {
        var contentId = "19450";
        var boutiqueId = "439892";
        var merchantId = "105064";

        var request = new URI(String.format("https://www.trendyol.com/casio/saat-p-%s?boutiqueId=%s&merchantId=%s", contentId, boutiqueId, merchantId));
        var response = webUrlToDeepLinkConverter.convert(request);

        Assert.assertEquals(response.getScheme(), "ty");
        Assert.assertNull(response.getAuthority());

        var responseQueryParams = LinkUtils.parseQuery(response.getQuery());

        Assert.assertEquals(responseQueryParams.size(), 4);
        Assert.assertEquals(responseQueryParams.get(QueryParameter.PAGE), PageType.PRODUCT_DETAIL.getLabel());
        Assert.assertEquals(responseQueryParams.get(QueryParameter.CONTENT_ID), contentId);
        Assert.assertEquals(responseQueryParams.get(QueryParameter.CAMPAIGN_ID), boutiqueId);
        Assert.assertEquals(responseQueryParams.get(QueryParameter.MERCHANT_ID), merchantId);
    }

    @SneakyThrows
    @Test
    public void testConvertSearch() {
        var search = "t-shirt";

        var request = new URI(String.format("https://www.trendyol.com/sr?q=%s", search));
        var response = webUrlToDeepLinkConverter.convert(request);

        Assert.assertEquals(response.getScheme(), "ty");
        Assert.assertNull(response.getAuthority());

        var responseQueryParams = LinkUtils.parseQuery(response.getQuery());

        Assert.assertEquals(responseQueryParams.size(), 2);
        Assert.assertEquals(responseQueryParams.get(QueryParameter.PAGE), PageType.SEARCH.getLabel());
        Assert.assertEquals(responseQueryParams.get(QueryParameter.QUERY), search);
    }

    @SneakyThrows
    @Test
    public void testConvertOthers() {
        var request = new URI("https://www.trendyol.com/settings/privacy");
        var response = webUrlToDeepLinkConverter.convert(request);

        Assert.assertEquals(response.getScheme(), "ty");
        Assert.assertNull(response.getAuthority());

        var responseQueryParams = LinkUtils.parseQuery(response.getQuery());

        Assert.assertEquals(responseQueryParams.size(), 1);
        Assert.assertEquals(responseQueryParams.get(QueryParameter.PAGE), PageType.OTHERS.getLabel());
    }

    private void initMocks() {
        var webUrlPathProperties = new WebUrlPathProperties();
        webUrlPathProperties.setProductDetailIdentifier("-p-");
        webUrlPathProperties.setSearchIdentifier("/sr");
        webUrlPathProperties.setProductDetailPrefix("/brand/name");

        var webUrlProperties = new WebUrlProperties();
        webUrlProperties.setHost("www.trendyol.com");
        webUrlProperties.setScheme("https");
        webUrlProperties.setPath(webUrlPathProperties);

        var deepLinkProperties = new DeepLinkProperties();
        deepLinkProperties.setScheme("ty");

        Mockito.when(properties.getWebUrl()).thenReturn(webUrlProperties);
        Mockito.when(properties.getDeepLink()).thenReturn(deepLinkProperties);
    }
}

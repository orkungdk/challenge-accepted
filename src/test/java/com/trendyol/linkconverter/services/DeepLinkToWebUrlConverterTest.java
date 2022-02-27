package com.trendyol.linkconverter.services;

import com.trendyol.linkconverter.beans.properties.TrendyolLinkProperties;
import com.trendyol.linkconverter.beans.properties.WebUrlPathProperties;
import com.trendyol.linkconverter.beans.properties.WebUrlProperties;
import com.trendyol.linkconverter.constants.QueryParameter;
import com.trendyol.linkconverter.services.impl.DeepLinkToWebUrlConverter;
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
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.net.URI;

/**
 * @author orkungedik
 */
@EnableConfigurationProperties
@RunWith(MockitoJUnitRunner.class)
public class DeepLinkToWebUrlConverterTest {
    @Spy
    @InjectMocks
    private DeepLinkToWebUrlConverter webUrlToDeepLinkConverter;
    @Mock
    private ValidationFacade validationFacade;
    @Mock
    private TrendyolLinkProperties properties;

    @Before
    public void setup() {
        initMocks();
        Mockito.doNothing().when(validationFacade).validateDeepLink(Mockito.any(URI.class));
    }

    @SneakyThrows
    @Test
    public void testConvertProductDetail() {
        var contentId = "19450";
        var boutiqueId = "439892";
        var merchantId = "105064";

        var request = new URI(String.format("ty://?Page=Product&MerchantId=%s&CampaignId=%s&ContentId=%s", merchantId, boutiqueId, contentId));
        var response = webUrlToDeepLinkConverter.convert(request);

        Assert.assertEquals(response.getScheme(), "https");
        Assert.assertEquals(response.getAuthority(), "www.trendyol.com");
        Assert.assertEquals(response.getPath(), "/brand/name-p-".concat(contentId));

        var responseQueryParams = LinkUtils.parseQuery(response.getQuery());

        Assert.assertEquals(responseQueryParams.size(), 2);
        Assert.assertEquals(responseQueryParams.get(QueryParameter.CAMPAIGN_ID), boutiqueId);
        Assert.assertEquals(responseQueryParams.get(QueryParameter.MERCHANT_ID), merchantId);
    }

    @SneakyThrows
    @Test
    public void testConvertSearch() {
        var search = "t-shirt";

        var request = new URI(String.format("ty://?Page=Search&Query=%s", search));
        var response = webUrlToDeepLinkConverter.convert(request);

        Assert.assertEquals(response.getScheme(), "https");
        Assert.assertEquals(response.getAuthority(), "www.trendyol.com");
        Assert.assertEquals(response.getPath(), "/sr");

        var responseQueryParams = LinkUtils.parseQuery(response.getQuery());

        Assert.assertEquals(responseQueryParams.size(), 1);
        Assert.assertEquals(responseQueryParams.get(QueryParameter.QUERY), search);
    }

    @SneakyThrows
    @Test
    public void testConvertOthers() {
        var request = new URI("ty://?Page=Favorites");
        var response = webUrlToDeepLinkConverter.convert(request);

        Assert.assertEquals(response.toString(), "https://www.trendyol.com");
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

        Mockito.when(properties.getWebUrl()).thenReturn(webUrlProperties);
    }
}

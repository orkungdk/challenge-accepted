package com.trendyol.linkconverter.api.controllers;

import com.trendyol.linkconverter.api.requests.LinkRequest;
import com.trendyol.linkconverter.api.responses.LinkResponse;
import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.services.proxy.LinkConversionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author orkungedik
 */
@RunWith(MockitoJUnitRunner.class)
public class LinkConversionControllerTest {

    @Spy
    @InjectMocks
    private LinkConversionController linkConversionController;
    @Mock
    private LinkConversionService linkConversionService;

    private LinkResponse linkResponse;

    @Before
    public void setup() {
        this.linkResponse = LinkResponse.builder().build();

        Mockito.when(linkConversionService.convert(Mockito.any(LinkRequest.class), Mockito.any(LinkType.class)))
                .thenReturn(linkResponse);
    }

    @Test
    public void testToDeepLinkConversion() {
        var response = linkConversionController.convertToDeepLink(new LinkRequest());

        Assert.assertNotNull(response);
        Assert.assertEquals(linkResponse, response.getBody());
    }

    @Test
    public void testToWebUrlConversion() {
        var response = linkConversionController.convertToWebUrl(new LinkRequest());

        Assert.assertNotNull(response);
        Assert.assertEquals(linkResponse, response.getBody());
    }
}

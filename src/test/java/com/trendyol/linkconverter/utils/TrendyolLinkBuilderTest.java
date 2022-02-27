package com.trendyol.linkconverter.utils;

import com.trendyol.linkconverter.constants.QueryParameter;
import com.trendyol.linkconverter.exceptions.TrendyolException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author orkungedik
 */
public final class TrendyolLinkBuilderTest {


    @Test
    public void testBuildWithoutQuery() {
        var result = new TrendyolLinkBuilder()
                .scheme("https")
                .host("www.trendyol.com")
                .path("/settings")
                .typeGetter(QueryParameter::getWebUrlLabel)
                .build();

        Assert.assertEquals("https://www.trendyol.com/settings", result.toString());
    }

    @Test
    public void testBuildWithQuery() {
        var result = new TrendyolLinkBuilder()
                .scheme("ty")
                .queryParameter(QueryParameter.PAGE, "Search")
                .queryParameters(Map.of(QueryParameter.QUERY, "phone"))
                .typeGetter(QueryParameter::getDeepLinkLabel)
                .build();

        Assert.assertEquals("ty://?Page=Search&Query=phone", result.toString());
    }

    @Test(expected = TrendyolException.class)
    public void testBuildWithQueryFail() {
        new TrendyolLinkBuilder()
                .scheme("ty")
                .queryParameter(QueryParameter.PAGE, "Search")
                .queryParameters(Map.of(QueryParameter.QUERY, "phone"))
                .build();
    }
}

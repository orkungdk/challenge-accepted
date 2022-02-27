package com.trendyol.linkconverter.utils;

import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.constants.PageType;
import com.trendyol.linkconverter.constants.QueryParameter;
import com.trendyol.linkconverter.exceptions.TrendyolException;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.util.LinkedHashMap;

/**
 * @author orkungedik
 */
public class LinkUtilsTest {

    @Test
    public void testGenerateURI() {
        URI uri = LinkUtils.generateURI("https://www.trendyol.com");

        Assert.assertEquals(uri.getScheme(), "https");
        Assert.assertEquals(uri.getAuthority(), "www.trendyol.com");
    }

    @Test(expected = TrendyolException.class)
    public void testGenerateURIFail() {
        LinkUtils.generateURI("https://www.trendyol.com?test=%C%");
    }

    @Test
    public void testReverseType() {
        LinkType type1 = LinkUtils.reverseType(LinkType.DEEP_LINK);
        Assert.assertEquals(type1, LinkType.WEB_URL);

        LinkType type2 = LinkUtils.reverseType(LinkType.WEB_URL);
        Assert.assertEquals(type2, LinkType.DEEP_LINK);
    }

    @Test(expected = TrendyolException.class)
    public void testReverseTypeFail() {
        LinkUtils.reverseType(LinkType.INVALID);
    }

    @Test
    public void testParseQuery() {
        var params = LinkUtils.parseQuery("Page=Product&ContentId=123");

        Assert.assertEquals(params.size(), 2);
        Assert.assertEquals(params.get(QueryParameter.PAGE), PageType.PRODUCT_DETAIL.getLabel());
        Assert.assertEquals(params.get(QueryParameter.CONTENT_ID), "123");
    }

    @Test
    public void testBuildQueryString() {
        var query = "Page=Product&ContentId=123";
        var map = new LinkedHashMap();
        map.put(QueryParameter.PAGE, "Product");
        map.put(QueryParameter.CONTENT_ID, "123");

        var result = LinkUtils.buildQueryString(map, QueryParameter::getDeepLinkLabel);

        Assert.assertEquals(result, query);
    }
}

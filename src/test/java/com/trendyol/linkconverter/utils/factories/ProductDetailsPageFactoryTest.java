package com.trendyol.linkconverter.utils.factories;

import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.exceptions.TrendyolException;
import com.trendyol.linkconverter.utils.LinkUtils;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;

/**
 * @author orkungedik
 */
public class ProductDetailsPageFactoryTest {

    private static final String CONTENT_ID = "12345";

    @Test
    public void testGenerateWebUrlSuccess() {
        var uri = getUri("https://www.trendyol.com/product/watch-p-");
        var page = ProductDetailsPageFactory.getInstance().generate(uri, LinkType.WEB_URL);

        Assert.assertEquals(page.getContentId(), CONTENT_ID);
    }

    @Test(expected = TrendyolException.class)
    public void testGenerateWebUrlFail() {
        var uri = getUri("https://www.trendyol.com/product/watch-");
        ProductDetailsPageFactory.getInstance().generate(uri, LinkType.WEB_URL);
    }

    @Test
    public void testGenerateDeepLinkSuccess() {
        var uri = getUri("ty://?Page=Product&ContentId=");
        var page = ProductDetailsPageFactory.getInstance().generate(uri, LinkType.DEEP_LINK);

        Assert.assertEquals(page.getContentId(), CONTENT_ID);
    }

    @Test(expected = TrendyolException.class)
    public void testGenerateDeepLinkFail() {
        var uri = getUri("ty://?Page=Product&MerchantId=");
        ProductDetailsPageFactory.getInstance().generate(uri, LinkType.DEEP_LINK);
    }

    /**
     * Executes URI creation by given link
     *
     * @param link uncompleted link. Content id will be inserted at the end of the string
     * @return {@link URI}
     */
    private URI getUri(String link) {
        return LinkUtils.generateURI(link.concat(CONTENT_ID));
    }
}

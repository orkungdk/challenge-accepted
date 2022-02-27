package com.trendyol.linkconverter.utils.factories;

import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.constants.PageType;
import com.trendyol.linkconverter.models.OthersPageDTO;
import com.trendyol.linkconverter.models.ProductDetailPageDTO;
import com.trendyol.linkconverter.models.SearchPageDTO;
import com.trendyol.linkconverter.models.TrendyolPageDTO;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;

/**
 * @author orkungedik
 */
public class TrendyolPageFactoryTest {

    @SneakyThrows
    @Test
    public void testProductDetail() {
        var page = TrendyolPageFactory.generate(new URI("https://trendyol.com/name-p-123"), PageType.PRODUCT_DETAIL, LinkType.WEB_URL);
        Assert.assertTrue(page instanceof ProductDetailPageDTO);
    }

    @SneakyThrows
    @Test
    public void testSearch() {
        var page = TrendyolPageFactory.generate(new URI("https://trendyol.com/sr?q=test"), PageType.SEARCH, LinkType.WEB_URL);
        Assert.assertTrue(page instanceof SearchPageDTO);
    }

    @SneakyThrows
    @Test
    public void testOthers() {
        var page = TrendyolPageFactory.generate(new URI("https://trendyol.com/settings"), PageType.OTHERS, LinkType.WEB_URL);
        Assert.assertTrue(page instanceof OthersPageDTO);
    }
}

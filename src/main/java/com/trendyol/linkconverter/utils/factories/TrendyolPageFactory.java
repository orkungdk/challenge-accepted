package com.trendyol.linkconverter.utils.factories;

import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.constants.PageType;
import com.trendyol.linkconverter.models.OthersPageDTO;
import com.trendyol.linkconverter.models.TrendyolPageDTO;

import java.net.URI;

/**
 * @author orkungedik
 */
public class TrendyolPageFactory {

    public static TrendyolPageDTO generate(URI uri, PageType pageType, LinkType linkType) {
        switch (pageType) {
            case PRODUCT_DETAIL:
                return ProductDetailsPageFactory.getInstance().generate(uri, linkType);
            case SEARCH:
                return SearchPageFactory.getInstance().generate(uri);
            default:
                return new OthersPageDTO();
        }
    }
}

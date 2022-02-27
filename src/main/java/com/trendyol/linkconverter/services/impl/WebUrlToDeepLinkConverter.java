package com.trendyol.linkconverter.services.impl;

import com.trendyol.linkconverter.beans.properties.TrendyolLinkProperties;
import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.constants.PageType;
import com.trendyol.linkconverter.constants.QueryParameter;
import com.trendyol.linkconverter.models.ProductDetailPageDTO;
import com.trendyol.linkconverter.services.proxy.LinkConverter;
import com.trendyol.linkconverter.utils.TrendyolLinkBuilder;
import com.trendyol.linkconverter.utils.factories.TrendyolPageFactory;
import com.trendyol.linkconverter.validations.ValidationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

/**
 * This service converts links from {@link LinkType#DEEP_LINK} to {@link LinkType#WEB_URL}
 *
 * @author orkungedik
 */
@Slf4j
@Service
public class WebUrlToDeepLinkConverter implements LinkConverter {

    @Autowired
    private ValidationFacade validationFacade;
    @Autowired
    private TrendyolLinkProperties properties;

    @Override
    public URI convert(URI uri) {
        validationFacade.validateWebUrl(uri);

        var pageType = resolvePageType(uri);
        log.info("Resolved page type is {}", pageType);

        var page = TrendyolPageFactory.generate(uri, pageType, LinkType.WEB_URL);

        if (pageType.equals(PageType.PRODUCT_DETAIL)) {
            var contentId = ((ProductDetailPageDTO) page).getContentId();
            page.getParameters().put(QueryParameter.CONTENT_ID, contentId);
        }

        return new TrendyolLinkBuilder()
                .scheme(properties.getDeepLink().getScheme())
                .queryParameter(QueryParameter.PAGE, page.getType().getLabel())
                .queryParameters(page.getParameters())
                .typeGetter(QueryParameter::getDeepLinkLabel)
                .build();
    }

    /**
     * For the {@link LinkType#WEB_URL}, page type can be resolved by {@link URI#getPath()}.
     * If path contains "-p-", then page type is {@link PageType#PRODUCT_DETAIL}
     * If path equals to "/sr", then page type is {@link PageType#SEARCH}
     * Otherwise it is {@link PageType#OTHERS}
     *
     * @param uri received {@link URI} value from request.
     * @return resolved {@link PageType}.
     */
    @Override
    public PageType resolvePageType(URI uri) {
        var path = uri.getPath();

        if (path.contains(properties.getWebUrl().getPath().getProductDetailIdentifier())) {
            return PageType.PRODUCT_DETAIL;
        } else if (path.equals(properties.getWebUrl().getPath().getSearchIdentifier())) {
            return PageType.SEARCH;
        } else {
            return PageType.OTHERS;
        }
    }

}

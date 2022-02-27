package com.trendyol.linkconverter.services.impl;

import com.trendyol.linkconverter.beans.properties.TrendyolLinkProperties;
import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.constants.PageType;
import com.trendyol.linkconverter.constants.QueryParameter;
import com.trendyol.linkconverter.models.ProductDetailPageDTO;
import com.trendyol.linkconverter.models.TrendyolPageDTO;
import com.trendyol.linkconverter.services.proxy.LinkConverter;
import com.trendyol.linkconverter.utils.LinkUtils;
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
public class DeepLinkToWebUrlConverter implements LinkConverter {

    @Autowired
    private ValidationFacade validationFacade;
    @Autowired
    private TrendyolLinkProperties properties;

    @Override
    public URI convert(URI uri) {
        validationFacade.validateDeepLink(uri);

        var pageType = resolvePageType(uri);
        log.info("Resolved page type is {}", pageType);

        var page = TrendyolPageFactory.generate(uri, pageType, LinkType.DEEP_LINK);
        var path = buildPath(page);

        return new TrendyolLinkBuilder()
                .scheme(properties.getWebUrl().getScheme())
                .host(properties.getWebUrl().getHost())
                .path(path)
                .queryParameters(page.getParameters())
                .typeGetter(QueryParameter::getWebUrlLabel)
                .build();
    }

    /**
     * For the {@link LinkType#DEEP_LINK}, page type can be resolved by {@link URI#getQuery()}.
     * it should be assigned to {@link QueryParameter#PAGE} in query parameters.
     *
     * @param uri received {@link URI} value from request.
     * @return resolved {@link PageType}.
     */
    @Override
    public PageType resolvePageType(URI uri) {
        var pageName = LinkUtils.parseQuery(uri.getQuery()).get(QueryParameter.PAGE);

        return PageType.of(pageName);
    }


    /**
     * Generate request path according to page
     *
     * @param page {@link TrendyolPageDTO}
     * @return
     */
    private String buildPath(TrendyolPageDTO page) {
        var path = "";

        if (page.getType().equals(PageType.PRODUCT_DETAIL)) {
            var contentId = ((ProductDetailPageDTO) page).getContentId();
            path = properties.getWebUrl().getPath().getProductDetailPrefix()
                    .concat(properties.getWebUrl().getPath().getProductDetailIdentifier())
                    .concat(contentId);
        } else if (page.getType().equals(PageType.SEARCH)) {
            path = properties.getWebUrl().getPath().getSearchIdentifier();
        }

        return path;
    }
}

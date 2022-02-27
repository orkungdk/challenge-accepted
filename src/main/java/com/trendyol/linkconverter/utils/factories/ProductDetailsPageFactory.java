package com.trendyol.linkconverter.utils.factories;

import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.constants.QueryParameter;
import com.trendyol.linkconverter.exceptions.ErrorType;
import com.trendyol.linkconverter.exceptions.TrendyolException;
import com.trendyol.linkconverter.models.ProductDetailPageDTO;
import com.trendyol.linkconverter.utils.LinkUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author orkungedik
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDetailsPageFactory {

    private static final Pattern PATTERN = Pattern.compile("-p-(.*?)(\\?|$)");

    private static ProductDetailsPageFactory instance;

    public static ProductDetailsPageFactory getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ProductDetailsPageFactory();
        }
        return instance;
    }

    public static ProductDetailPageDTO generate(URI uri, LinkType linkType) {
        var queryParameters = LinkUtils.parseQuery(uri.getQuery());
        var contentId = linkType.equals(LinkType.WEB_URL)
                ? parseContentId(uri)
                : queryParameters.get(QueryParameter.CONTENT_ID);

        if (contentId == null) {
            throw new TrendyolException(ErrorType.INVALID_REQUEST, "Content id is not found for product detail.");
        }

        return new ProductDetailPageDTO(queryParameters, contentId);
    }

    private static String parseContentId(URI uri) {
        Matcher matcher = PATTERN.matcher(uri.getPath());

        if (matcher.find() && !matcher.group(1).trim().isEmpty()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}

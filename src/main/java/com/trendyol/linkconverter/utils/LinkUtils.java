package com.trendyol.linkconverter.utils;

import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.constants.QueryParameter;
import com.trendyol.linkconverter.exceptions.ErrorType;
import com.trendyol.linkconverter.exceptions.TrendyolException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author orkungedik
 */
@UtilityClass
public final class LinkUtils {

    /**
     * Creates an instance of {@link URI} in case {@code link is available}.
     *
     * @param link {@link String} value of to be generated {@link URI}
     * @return {@link URI}
     * @throws TrendyolException if link format is not valid.
     */
    public static URI generateURI(String link) {
        try {
            return new URI(link);
        } catch (URISyntaxException e) {
            throw new TrendyolException(ErrorType.INVALID_REQUEST, "Link format is not valid.");
        }
    }

    /**
     * @param type {@link LinkType}
     * @return {@link LinkType}
     * @implSpec Available types for {@code type} is either {@link LinkType#WEB_URL} or {@link LinkType#DEEP_LINK}.
     * @implNote Possible outcome as follows:
     * - Input: {@link LinkType#WEB_URL}   -> Output: {@link LinkType#DEEP_LINK}.
     * - Input: {@link LinkType#DEEP_LINK} -> Output: {@link LinkType#WEB_URL}.
     */
    public static LinkType reverseType(LinkType type) {
        if (type.equals(LinkType.INVALID)) {
            throw new TrendyolException(ErrorType.INTERNAL_SERVER_ERROR, "Invalid link type cannot be reversed.");
        }
        return type.equals(LinkType.WEB_URL) ? LinkType.DEEP_LINK : LinkType.WEB_URL;
    }

    /**
     * Parses query parameters into the {@link Map}
     *
     * @param query received request query
     * @return {@link Map<QueryParameter, String>}
     */
    public Map<QueryParameter, String> parseQuery(@Nullable String query) {
        if (StringUtils.isEmpty(query)) {
            return new HashMap();
        }
        return Arrays.stream(query.split("&"))
                .map(param -> param.split("="))
                .filter(param -> Objects.nonNull(param[0]))
                .collect(Collectors.toMap(param -> QueryParameter.of(param[0]), param -> param[1]));
    }

    public String buildQueryString(LinkedHashMap<QueryParameter, String> parameters, Function<QueryParameter, String> typeGetter) {
        return parameters.entrySet().stream()
                .filter(entry -> !typeGetter.apply(entry.getKey()).isEmpty()) // Filter out if entry does not contain a value for specified type
                .map(entry -> typeGetter.apply(entry.getKey()).concat("=").concat(entry.getValue())) // Concatenate map key and value
                .collect(Collectors.joining("&")); // Joins all query parameters with '&' delimiter

    }
}

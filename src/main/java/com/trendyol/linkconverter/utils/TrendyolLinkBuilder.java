package com.trendyol.linkconverter.utils;

import com.trendyol.linkconverter.constants.QueryParameter;
import com.trendyol.linkconverter.exceptions.ErrorType;
import com.trendyol.linkconverter.exceptions.TrendyolException;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author orkungedik
 */
@NoArgsConstructor
public final class TrendyolLinkBuilder {

    private static final String SCHEME_POSTFIX = "://";

    private static final String QUERY_PREFIX = "?";


    private LinkedHashMap<QueryParameter, String> parameters = new LinkedHashMap<>();

    private Function<QueryParameter, String> typeGetter;

    private String scheme = "";

    private String host = "";

    private String path = "";

    public TrendyolLinkBuilder scheme(@Nullable String scheme) {
        this.scheme = scheme;
        return this;
    }

    public TrendyolLinkBuilder host(@Nullable String host) {
        this.host = host;
        return this;
    }

    public TrendyolLinkBuilder path(@Nullable String path) {
        this.path = path;
        return this;
    }

    public TrendyolLinkBuilder queryParameters(@Nullable Map<QueryParameter, String> parameters) {
        if (parameters != null) {
            this.parameters.putAll(parameters);
        }
        return this;
    }


    public TrendyolLinkBuilder queryParameter(@Nullable QueryParameter parameter, String value) {
        this.parameters.put(parameter, value);
        return this;
    }

    public TrendyolLinkBuilder typeGetter(@NonNull Function<QueryParameter, String> typeGetter) {
        this.typeGetter = typeGetter;
        return this;
    }

    public URI build() {
        if (typeGetter == null) {
            throw new TrendyolException(ErrorType.INTERNAL_SERVER_ERROR, "Type getter cannot be null in {}.", this.getClass().getSimpleName());
        }

        var builder = new StringBuilder()
                .append(scheme)
                .append(SCHEME_POSTFIX)
                .append(host)
                .append(path);

        var queryString = LinkUtils.buildQueryString(parameters, typeGetter);
        if (queryString.isEmpty()) {
            return LinkUtils.generateURI(builder.toString());
        } else {
            return LinkUtils.generateURI(builder.append(QUERY_PREFIX).append(queryString).toString());
        }
    }
}

package com.trendyol.linkconverter.utils.factories;

import com.trendyol.linkconverter.models.SearchPageDTO;
import com.trendyol.linkconverter.utils.LinkUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.Objects;

/**
 * @author orkungedik
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchPageFactory {

    private static SearchPageFactory instance;

    public static SearchPageFactory getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SearchPageFactory();
        }
        return instance;
    }

    public static SearchPageDTO generate(URI uri) {
        var params = LinkUtils.parseQuery(uri.getQuery());

        return new SearchPageDTO(params);
    }

}

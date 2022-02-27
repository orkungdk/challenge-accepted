package com.trendyol.linkconverter.models;

import com.trendyol.linkconverter.constants.PageType;
import com.trendyol.linkconverter.constants.QueryParameter;

import java.util.Map;

/**
 * @author orkungedik
 */
public class SearchPageDTO extends TrendyolPageDTO {

    public SearchPageDTO(Map<QueryParameter, String> parameters) {
        super(PageType.SEARCH, parameters);
    }
}

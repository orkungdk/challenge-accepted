package com.trendyol.linkconverter.models;

import com.trendyol.linkconverter.constants.PageType;
import com.trendyol.linkconverter.constants.QueryParameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author orkungedik
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class TrendyolPageDTO {

    private PageType type;

    private Map<QueryParameter, String> parameters;

}

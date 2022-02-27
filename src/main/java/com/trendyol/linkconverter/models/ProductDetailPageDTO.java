package com.trendyol.linkconverter.models;

import com.trendyol.linkconverter.constants.PageType;
import com.trendyol.linkconverter.constants.QueryParameter;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author orkungedik
 */
@Getter
@Setter
public class ProductDetailPageDTO extends TrendyolPageDTO {

    private String contentId;

    public ProductDetailPageDTO(Map<QueryParameter, String> parameters, String contentId) {
        super(PageType.PRODUCT_DETAIL, parameters);
        this.contentId = contentId;
    }
}

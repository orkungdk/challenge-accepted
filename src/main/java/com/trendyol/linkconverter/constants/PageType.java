package com.trendyol.linkconverter.constants;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author orkungedik
 */
@Getter
public enum PageType {

    PRODUCT_DETAIL("Product"), SEARCH("Search"), OTHERS("Home");

    private String label;

    PageType(String label) {
        this.label = label;
    }

    public static PageType of(String label) {
        return Arrays.stream(PageType.values())
                .filter(type -> type.getLabel().equals(label))
                .findAny()
                .orElse(OTHERS);
    }
}

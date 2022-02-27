package com.trendyol.linkconverter.constants;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author orkungedik
 */
@Getter
public enum QueryParameter {

    PAGE("", "Page"),

    QUERY("q", "Query"),

    CONTENT_ID("", "ContentId"),

    CAMPAIGN_ID("boutiqueId", "CampaignId"),

    MERCHANT_ID("merchantId", "MerchantId");

    private String webUrlLabel;

    private String deepLinkLabel;

    QueryParameter(String webUrlLabel, String deepLinkLabel) {
        this.webUrlLabel = webUrlLabel;
        this.deepLinkLabel = deepLinkLabel;
    }

    public String label(LinkType linkType) {
        return linkType.equals(LinkType.WEB_URL) ? this.webUrlLabel : this.deepLinkLabel;
    }

    public static QueryParameter of(String value) {
        return Arrays.stream(QueryParameter.values())
                .filter(enumerator -> enumerator.deepLinkLabel.equals(value) || enumerator.webUrlLabel.equals(value))
                .findFirst()
                .orElse(null);
    }
}

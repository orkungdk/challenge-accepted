package com.trendyol.linkconverter.api.responses;

import com.trendyol.linkconverter.models.LinkConversionDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author orkungedik
 */
@Getter
@Setter
@Builder
@ToString
public class LinkResponse {

    private LinkConversionDTO from;

    private LinkConversionDTO to;

}

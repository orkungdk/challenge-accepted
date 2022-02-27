package com.trendyol.linkconverter.models;

import com.trendyol.linkconverter.constants.LinkType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URI;

/**
 * @author orkungedik
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkConversionDTO {

    private URI link;

    private LinkType type;

}

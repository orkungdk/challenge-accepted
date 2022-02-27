package com.trendyol.linkconverter.services.proxy;

import com.trendyol.linkconverter.api.requests.LinkRequest;
import com.trendyol.linkconverter.api.responses.LinkResponse;
import com.trendyol.linkconverter.constants.LinkType;

/**
 * @author orkungedik
 */
public interface LinkConversionService {

    LinkResponse convert(LinkRequest request, LinkType fromLinkType);

}

package com.trendyol.linkconverter.services.impl;

import com.trendyol.linkconverter.api.requests.LinkRequest;
import com.trendyol.linkconverter.api.responses.LinkResponse;
import com.trendyol.linkconverter.beans.LinkConversionSession;
import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.models.LinkConversionDTO;
import com.trendyol.linkconverter.services.proxy.LinkConversionService;
import com.trendyol.linkconverter.utils.LinkUtils;
import com.trendyol.linkconverter.utils.TrendyolBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author orkungedik
 */
@Slf4j
@Service
public class LinkConversionServiceImpl implements LinkConversionService {

    @Autowired
    private TrendyolBeanFactory beanFactory;
    @Autowired
    private LinkConversionSession session;

    public LinkResponse convert(LinkRequest request, LinkType fromLinkType) {
        session.setRequest(request.getLink());

        var fromLink = LinkUtils.generateURI(request.getLink());
        var converter = beanFactory.getBean(fromLinkType);

        var toLinkType = LinkUtils.reverseType(fromLinkType);
        var convertedLink = converter.convert(fromLink);

        log.info("Link conversion is completed from {} to {}. Generated url is {}.", fromLinkType, toLinkType, convertedLink);

        var fromConversion = new LinkConversionDTO(fromLink, fromLinkType);
        var toConversion = new LinkConversionDTO(convertedLink, toLinkType);

        session.setResponse(convertedLink.toString());

        return LinkResponse.builder().from(fromConversion).to(toConversion).build();
    }
}
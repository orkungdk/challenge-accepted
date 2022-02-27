package com.trendyol.linkconverter.api.controllers;

import com.trendyol.linkconverter.api.requests.LinkRequest;
import com.trendyol.linkconverter.api.responses.TrendyolResponse;
import com.trendyol.linkconverter.constants.LinkType;
import com.trendyol.linkconverter.services.proxy.LinkConversionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author orkungedik
 */
@Slf4j
@RestController
@RequestMapping("/v1/convert")
@Tag(name = "Trendyol Link Conversion API")
public class LinkConversionController {

    @Autowired
    private LinkConversionService linkConversionService;

    @PostMapping("/to-deep-link")
    @Operation(summary = "Converts from web-url to deep-link.")
    public ResponseEntity convertToDeepLink(@Validated @RequestBody LinkRequest request) {
        log.info("Request has been received for link conversion from web url to deep link.");
        var response = linkConversionService.convert(request, LinkType.WEB_URL);

        return TrendyolResponse.build(response);
    }

    @PostMapping("/to-web-url")
    @Operation(summary = "Converts from deep-link to web-url.")
    public ResponseEntity convertToWebUrl(@Validated @RequestBody LinkRequest request) {
        log.info("Request has been received for link conversion from deep link to web url.");
        var response = linkConversionService.convert(request, LinkType.DEEP_LINK);

        return TrendyolResponse.build(response);
    }
}

package com.trendyol.linkconverter.api.controllers;

import com.trendyol.linkconverter.api.responses.TrendyolResponse;
import com.trendyol.linkconverter.services.proxy.AuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author orkungedik
 */
@Slf4j
@RestController
@RequestMapping("/v1/audit")
@Tag(name = "Trendyol Audit Search API")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @GetMapping
    @Operation(summary = "Retrieve audit history.")
    public TrendyolResponse retrieveAll() {
        log.info("Request has been received to retrieve all audits.");
        var response = auditService.retrieveAll();

        return TrendyolResponse.build(response);
    }

}

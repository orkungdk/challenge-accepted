package com.trendyol.linkconverter.validations;

import com.trendyol.linkconverter.exceptions.ErrorMessage;
import com.trendyol.linkconverter.exceptions.TrendyolException;
import com.trendyol.linkconverter.validations.validators.AbstractValidator;
import com.trendyol.linkconverter.validations.validators.DeepLinkPageValidator;
import com.trendyol.linkconverter.validations.validators.DeepLinkValidator;
import com.trendyol.linkconverter.validations.validators.WebUrlValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author orkungedik
 */
@Slf4j
@Service
public class ValidationFacade {

    @Autowired
    private DeepLinkValidator deepLinkValidator;
    @Autowired
    private DeepLinkPageValidator deepLinkPageValidator;
    @Autowired
    private WebUrlValidator webUrlValidator;

    public void validateDeepLink(URI link) {
        validate(link, deepLinkValidator, deepLinkPageValidator);
    }

    public void validateWebUrl(URI link) {
        validate(link, webUrlValidator);
    }

    /**
     * Executes given {@link AbstractValidator} list and collects {@link com.trendyol.linkconverter.exceptions.ErrorMessage}.
     * If collected error message list is not empty then throws exception.
     *
     * @param link       {@link URI}
     * @param validators {@link AbstractValidator[]}
     * @throws {@link TrendyolException}
     */
    private void validate(URI link, AbstractValidator... validators) {
        List<ErrorMessage> errorMessages = new ArrayList<>();

        for (var validator : validators) {
            errorMessages.addAll(validator.validate(link));
        }

        if (!errorMessages.isEmpty()) {
            throw new TrendyolException(errorMessages);
        }
        log.info("Validation successfully completed.");
    }
}

package com.trendyol.linkconverter.validations.validators;

import com.trendyol.linkconverter.beans.properties.WebUrlProperties;
import com.trendyol.linkconverter.exceptions.ErrorMessage;
import com.trendyol.linkconverter.exceptions.ErrorType;
import com.trendyol.linkconverter.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author orkungedik
 */
@Service
public class WebUrlValidator extends AbstractValidator {

    @Autowired
    private WebUrlProperties properties;

    @Override
    public List<ErrorMessage> validate(URI link) {
        var errorMessages = new ArrayList<ErrorMessage>();

        // Validates web url scheme is "https"
        AssertUtils.equals(errorMessages, properties.getScheme(), link.getScheme(), ErrorType.INVALID_REQUEST, "Invalid scheme.");
        // Validates web url host is "www.trendyol.com"
        AssertUtils.equals(errorMessages, properties.getHost(), link.getAuthority(), ErrorType.INVALID_REQUEST, "Invalid host.");

        return errorMessages;
    }
}

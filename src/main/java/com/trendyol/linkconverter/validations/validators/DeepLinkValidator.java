package com.trendyol.linkconverter.validations.validators;

import com.trendyol.linkconverter.beans.properties.DeepLinkProperties;
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
public class DeepLinkValidator extends AbstractValidator {

    @Autowired
    private DeepLinkProperties properties;

    @Override
    public List<ErrorMessage> validate(URI link) {
        var errorMessages = new ArrayList<ErrorMessage>();

        // Validates deep link scheme is "ty"
        AssertUtils.equals(errorMessages, link.getScheme(), properties.getScheme(), ErrorType.INVALID_REQUEST, "Invalid scheme.");
        // Validates deep link host is null
        AssertUtils.isNull(errorMessages, link.getAuthority(), ErrorType.INVALID_REQUEST, "Invalid host.");

        return errorMessages;
    }
}

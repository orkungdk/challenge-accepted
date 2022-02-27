package com.trendyol.linkconverter.validations.validators;

import com.trendyol.linkconverter.constants.QueryParameter;
import com.trendyol.linkconverter.exceptions.ErrorMessage;
import com.trendyol.linkconverter.exceptions.ErrorType;
import com.trendyol.linkconverter.utils.AssertUtils;
import com.trendyol.linkconverter.utils.LinkUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author orkungedik
 */
@Service
public class DeepLinkPageValidator extends AbstractValidator {

    @Override
    public List<ErrorMessage> validate(URI link) {
        var errorMessages = new ArrayList<ErrorMessage>();

        var pageParameter = LinkUtils.parseQuery(link.getQuery()).get(QueryParameter.PAGE);
        // Validates deep link contains "Page" query parameter
        AssertUtils.nonNull(errorMessages, pageParameter, ErrorType.INVALID_REQUEST, "Page query parameter is missing.");

        return errorMessages;
    }
}

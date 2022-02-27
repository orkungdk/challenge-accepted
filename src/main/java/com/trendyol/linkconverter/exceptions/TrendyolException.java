package com.trendyol.linkconverter.exceptions;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Collections;

/**
 * @author orkungedik
 */
@Getter
public class TrendyolException extends RuntimeException {

    private static final Integer DEFAULT_HTTP_STATUS_CODE = 500;

    private Collection<ErrorMessage> errors;

    private Integer httpStatusCode;

    public TrendyolException(Collection<ErrorMessage> errors) {
        this.errors = errors;
        // httpStatusCode is set to first error message's status.
        var optional = errors.stream().findFirst();

        this.httpStatusCode = optional.isPresent()
                ? optional.get().getStatus()
                : DEFAULT_HTTP_STATUS_CODE;
    }

    public TrendyolException(ErrorType errorType, @Nullable String detail, Object... params) {
        var errorMessage = new ErrorMessage();
        errorMessage.setTitle(errorType.getTitle());
        errorMessage.setStatus(errorType.getStatus());
        if (StringUtils.isNotEmpty(detail)) {
            errorMessage.setDetails(String.format(detail, params));
        }

        this.errors = (Collections.singleton(errorMessage));
        this.httpStatusCode = errorType.getStatus();
    }
}

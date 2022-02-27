package com.trendyol.linkconverter.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author orkungedik
 */
@Getter
@Setter
public class DetailedErrorMessage extends ErrorMessage {

    private Object expected;

    private Object actual;

    public DetailedErrorMessage(ErrorType errorType, String details, Object expected, Object actual) {
        super(errorType, details);
        this.expected = expected;
        this.actual = actual;
    }
}

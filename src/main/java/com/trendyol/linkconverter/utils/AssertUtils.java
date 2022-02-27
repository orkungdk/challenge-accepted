package com.trendyol.linkconverter.utils;

import com.trendyol.linkconverter.exceptions.DetailedErrorMessage;
import com.trendyol.linkconverter.exceptions.ErrorMessage;
import com.trendyol.linkconverter.exceptions.ErrorType;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

/**
 * @author orkungedik
 */
@UtilityClass
public final class AssertUtils {

    public static void equals(List<ErrorMessage> errors, Object expected, Object actual, ErrorType errorType, String errorDetail) {
        if (!Objects.equals(expected, actual)) {
            errors.add(new DetailedErrorMessage(errorType, errorDetail, expected, actual));
        }
    }

    public static void isNull(List<ErrorMessage> errors, Object actual, ErrorType errorType, String errorDetail) {
        if (Objects.nonNull(actual)) {
            errors.add(new ErrorMessage(errorType, errorDetail));
        }
    }

    public static void nonNull(List<ErrorMessage> errors, Object actual, ErrorType errorType, String errorDetail) {
        if (Objects.isNull(actual)) {
            errors.add(new ErrorMessage(errorType, errorDetail));
        }
    }
}
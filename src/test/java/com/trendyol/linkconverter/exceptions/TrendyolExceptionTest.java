package com.trendyol.linkconverter.exceptions;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author orkungedik
 */
public class TrendyolExceptionTest extends RuntimeException {

    @Test
    public void testWithCollection() {
        var errorMessages = List.of(
                new ErrorMessage(ErrorType.INVALID_REQUEST, null),
                new ErrorMessage(ErrorType.INTERNAL_SERVER_ERROR, null)
        );
        var exception = new TrendyolException(errorMessages);

        Assert.assertEquals(exception.getHttpStatusCode().intValue(), ErrorType.INVALID_REQUEST.getStatus());
        Assert.assertEquals(exception.getErrors().size(), errorMessages.size());
    }

    @Test
    public void testWithError() {
        var detail = "hello %s";
        var parameter = "trendyol";
        var exception = new TrendyolException(ErrorType.INVALID_REQUEST, detail, parameter);

        Assert.assertEquals(exception.getErrors().iterator().next().getDetails(), detail.replace("%s", parameter));
        Assert.assertEquals(exception.getErrors().iterator().next().getTitle(), ErrorType.INVALID_REQUEST.getTitle());
    }
}

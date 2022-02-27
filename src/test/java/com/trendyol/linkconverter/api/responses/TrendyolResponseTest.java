package com.trendyol.linkconverter.api.responses;

import com.trendyol.linkconverter.exceptions.ErrorType;
import com.trendyol.linkconverter.exceptions.TrendyolException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

/**
 * @author orkungedik
 */
public class TrendyolResponseTest {

    @Test
    public void testNoContent() {
        var response = TrendyolResponse.build(null);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testOk() {
        var response = TrendyolResponse.build(new Object());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testError() {
        var response = TrendyolResponse.error(new TrendyolException(ErrorType.INTERNAL_SERVER_ERROR, null));
        Assert.assertEquals(response.getStatusCode().value(), ErrorType.INTERNAL_SERVER_ERROR.getStatus());
    }

}

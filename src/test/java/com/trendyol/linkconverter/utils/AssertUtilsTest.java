package com.trendyol.linkconverter.utils;

import com.trendyol.linkconverter.exceptions.ErrorMessage;
import com.trendyol.linkconverter.exceptions.ErrorType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author orkungedik
 */
public class AssertUtilsTest {

    private List<ErrorMessage> errorMessages;

    @Before
    public void setup() {
        errorMessages = new ArrayList<>();
    }

    @Test
    public void testEquals() {
        String val = "trendyol";

        AssertUtils.equals(errorMessages, val, val, ErrorType.INVALID_REQUEST, "");
        Assert.assertTrue(errorMessages.isEmpty());

        AssertUtils.equals(errorMessages, val, "", ErrorType.INVALID_REQUEST, "");
        Assert.assertEquals(errorMessages.size(), 1);
    }

    @Test
    public void testIsNull() {
        AssertUtils.isNull(errorMessages, null, ErrorType.INVALID_REQUEST, "");
        Assert.assertTrue(errorMessages.isEmpty());

        AssertUtils.isNull(errorMessages, new Object(), ErrorType.INVALID_REQUEST, "");
        Assert.assertEquals(errorMessages.size(), 1);
    }

    @Test
    public void testNonNull() {
        AssertUtils.nonNull(errorMessages, new Object(), ErrorType.INVALID_REQUEST, "");
        Assert.assertTrue(errorMessages.isEmpty());

        AssertUtils.nonNull(errorMessages, null, ErrorType.INVALID_REQUEST, "");
        Assert.assertEquals(errorMessages.size(), 1);
    }
}

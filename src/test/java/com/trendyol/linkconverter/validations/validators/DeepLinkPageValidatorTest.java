package com.trendyol.linkconverter.validations.validators;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URI;

/**
 * @author orkungedik
 */
@RunWith(MockitoJUnitRunner.class)
public class DeepLinkPageValidatorTest {

    @Spy
    @InjectMocks
    private DeepLinkPageValidator validator;

    @SneakyThrows
    @Test
    public void testValidate() {
        var errorMessages = validator.validate(new URI("ty://?ContentId=123"));

        Assert.assertEquals(errorMessages.size(), 1);
    }
}

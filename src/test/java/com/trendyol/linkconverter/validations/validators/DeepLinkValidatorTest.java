package com.trendyol.linkconverter.validations.validators;

import com.trendyol.linkconverter.beans.properties.DeepLinkProperties;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URI;

/**
 * @author orkungedik
 */
@RunWith(MockitoJUnitRunner.class)
public class DeepLinkValidatorTest {

    @Spy
    @InjectMocks
    private DeepLinkValidator validator;
    @Mock
    private DeepLinkProperties properties;


    @SneakyThrows
    @Test
    public void testValidate() {
        Mockito.when(properties.getScheme()).thenReturn("ty");

        var errorMessages = validator.validate(new URI("tr://trendyol?ContentId=123"));

        Assert.assertEquals(errorMessages.size(), 2);
    }
}

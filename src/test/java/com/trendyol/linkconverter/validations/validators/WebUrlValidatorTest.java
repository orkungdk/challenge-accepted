package com.trendyol.linkconverter.validations.validators;

import com.trendyol.linkconverter.beans.properties.WebUrlProperties;
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
public class WebUrlValidatorTest {

    @Spy
    @InjectMocks
    private WebUrlValidator validator;
    @Mock
    private WebUrlProperties properties;


    @SneakyThrows
    @Test
    public void testValidate() {
        Mockito.when(properties.getHost()).thenReturn("www.trendyol.com");
        Mockito.when(properties.getScheme()).thenReturn("https");

        var errorMessages = validator.validate(new URI("http://www.trendyol.com.tr?ContentId=123"));

        Assert.assertEquals(errorMessages.size(), 2);
    }
}

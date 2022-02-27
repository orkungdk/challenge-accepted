package com.trendyol.linkconverter.validations;

import com.trendyol.linkconverter.exceptions.ErrorMessage;
import com.trendyol.linkconverter.exceptions.TrendyolException;
import com.trendyol.linkconverter.validations.validators.*;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URI;
import java.util.List;

/**
 * @author orkungedik
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidationFacadeTest {

    @Spy
    @InjectMocks
    private ValidationFacade validationFacade;
    @Mock
    private DeepLinkValidator deepLinkValidator;
    @Mock
    private DeepLinkPageValidator deepLinkPageValidator;
    @Mock
    private WebUrlValidator webUrlValidator;

    @SneakyThrows
    @Test
    public void testValidateDeepLink() {
        validationFacade.validateDeepLink(new URI("ty://?Page=Search&Query=test"));
    }

    @SneakyThrows
    @Test(expected = TrendyolException.class)
    public void testValidateWebUrl() {
        var uri = new URI("https://ww.trendyol.com");

        Mockito.when(webUrlValidator.validate(uri)).thenReturn(List.of(new ErrorMessage()));

        validationFacade.validateWebUrl(uri);
    }
}

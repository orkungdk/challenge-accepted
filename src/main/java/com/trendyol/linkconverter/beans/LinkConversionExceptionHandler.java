package com.trendyol.linkconverter.beans;

import com.trendyol.linkconverter.api.responses.TrendyolResponse;
import com.trendyol.linkconverter.exceptions.TrendyolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

/**
 * @author orkungedik
 */
@ControllerAdvice
public class LinkConversionExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private LinkConversionSession session;

    @ExceptionHandler({TrendyolException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TrendyolResponse handleException(TrendyolException exception) {
        session.setResponse(exception.getErrors().stream()
                .map(error -> error.getTitle())
                .collect(Collectors.toSet()).stream()
                .collect(Collectors.joining(" | ")));

        return TrendyolResponse.error(exception);
    }

}

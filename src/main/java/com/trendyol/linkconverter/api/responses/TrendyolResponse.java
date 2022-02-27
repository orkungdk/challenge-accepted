package com.trendyol.linkconverter.api.responses;

import com.trendyol.linkconverter.exceptions.TrendyolException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

/**
 * @author orkungedik
 */
@Getter
@Setter
public class TrendyolResponse<T extends Object> extends ResponseEntity<T> {

    public TrendyolResponse(HttpStatus status) {
        super(status);
    }

    public TrendyolResponse(T body, HttpStatus status) {
        super(body, status);
    }

    /**
     * Returns an instance of {@link TrendyolResponse} with an status code depending on the given parameter.
     *
     * @param object the response object
     * @return {@link TrendyolResponse}
     */
    public static TrendyolResponse build(Object object) {
        if (Objects.isNull(object)) {
            return new TrendyolResponse(HttpStatus.NO_CONTENT);
        } else {
            return new TrendyolResponse(object, HttpStatus.OK);
        }
    }

    /**
     * Returns an instance of {@link TrendyolResponse} with an status code depending on the given parameter.
     *
     * @param e {@link TrendyolException}
     * @return {@link TrendyolResponse}
     */
    public static TrendyolResponse error(TrendyolException e) {
        return new TrendyolResponse(e.getErrors(), HttpStatus.valueOf(e.getHttpStatusCode()));
    }
}

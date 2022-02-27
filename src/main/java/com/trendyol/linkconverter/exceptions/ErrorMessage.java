package com.trendyol.linkconverter.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author orkungedik
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private Integer status;

    private String title;

    private String details;

    public ErrorMessage(ErrorType errorType, String details) {
        this.status = errorType.getStatus();
        this.title = errorType.getTitle();
        this.details = details;
    }

}

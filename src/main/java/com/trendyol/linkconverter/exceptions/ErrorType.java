package com.trendyol.linkconverter.exceptions;

import lombok.Getter;

/**
 * @author orkungedik
 */
@Getter
public enum ErrorType {

    INVALID_REQUEST("Invalid Request", 400),
    INTERNAL_SERVER_ERROR("Internal Server Error", 500);

    private String title;

    private int status;

    ErrorType(String title, int status) {
        this.title = title;
        this.status = status;
    }

}

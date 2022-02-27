package com.trendyol.linkconverter.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author orkungedik
 */
@Getter
@Setter
@Component
@RequestScope
public class LinkConversionSession {

    private String path;

    private String request;

    private String response;

}

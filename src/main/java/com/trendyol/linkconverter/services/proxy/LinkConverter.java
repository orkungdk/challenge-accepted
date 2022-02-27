package com.trendyol.linkconverter.services.proxy;

import com.trendyol.linkconverter.constants.PageType;

import java.net.URI;

/**
 * @author orkungedik
 */
public interface LinkConverter {

    URI convert(URI uri);

    PageType resolvePageType(URI uri);

}

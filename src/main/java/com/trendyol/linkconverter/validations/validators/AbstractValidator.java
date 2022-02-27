package com.trendyol.linkconverter.validations.validators;

import com.trendyol.linkconverter.exceptions.ErrorMessage;

import java.net.URI;
import java.util.List;

/**
 * @author orkungedik
 */
public abstract class AbstractValidator<I extends URI> {

    public abstract List<ErrorMessage> validate(I link);

}

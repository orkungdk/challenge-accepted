package com.trendyol.linkconverter.beans.mappers;

import com.trendyol.linkconverter.persistence.entities.TrendyolEntity;

/**
 * @author orkungedik
 */
public abstract class AbstractMapper<I extends Object, O extends TrendyolEntity> {

    public abstract I convert(O entity);

    public abstract O convert(I model);

}

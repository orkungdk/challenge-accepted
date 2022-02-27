package com.trendyol.linkconverter.beans.mappers;

import com.trendyol.linkconverter.beans.LinkConversionSession;
import com.trendyol.linkconverter.persistence.entities.AuditEntity;
import org.mapstruct.Mapper;

/**
 * @author orkungedik
 */
@Mapper(componentModel = "spring")
public abstract class AuditMapper extends AbstractMapper<LinkConversionSession, AuditEntity> {
}

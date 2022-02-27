package com.trendyol.linkconverter.services.proxy;

import com.trendyol.linkconverter.persistence.entities.AuditEntity;

import java.util.List;

/**
 * @author orkungedik
 */
public interface AuditService {

    List<AuditEntity> retrieveAll();

}

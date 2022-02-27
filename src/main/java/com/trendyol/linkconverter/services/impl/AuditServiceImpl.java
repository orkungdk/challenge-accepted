package com.trendyol.linkconverter.services.impl;

import com.trendyol.linkconverter.persistence.entities.AuditEntity;
import com.trendyol.linkconverter.persistence.repositories.AuditRepository;
import com.trendyol.linkconverter.services.proxy.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author orkungedik
 */
@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditRepository repository;

    @Override
    public List<AuditEntity> retrieveAll() {
        return repository.findAll();
    }
}

package com.trendyol.linkconverter.persistence.repositories;

import com.trendyol.linkconverter.persistence.entities.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author orkungedik
 */
@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {
}

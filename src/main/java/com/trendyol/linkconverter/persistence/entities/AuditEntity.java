package com.trendyol.linkconverter.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.Instant;

/**
 * @author orkungedik
 */
@Getter
@Setter
@Entity
@Table(name = AuditEntity.TABLE_NAME)
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity extends TrendyolEntity {

    private String path;

    private String request;

    private String response;

    @CreatedDate
    private Instant createdAt;

    protected static final String TABLE_NAME = "audit_logs";

}

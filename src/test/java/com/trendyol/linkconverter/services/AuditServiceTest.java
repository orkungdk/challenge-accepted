package com.trendyol.linkconverter.services;

import com.trendyol.linkconverter.persistence.entities.AuditEntity;
import com.trendyol.linkconverter.persistence.repositories.AuditRepository;
import com.trendyol.linkconverter.services.impl.AuditServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

/**
 * @author orkungedik
 */
@RunWith(MockitoJUnitRunner.class)
public class AuditServiceTest {

    @Spy
    @InjectMocks
    private AuditServiceImpl auditService;
    @Mock
    private AuditRepository repository;

    private List<AuditEntity> entities = List.of(new AuditEntity());

    @Before
    public void setup() {
        Mockito.when(repository.findAll()).thenReturn(entities);
    }

    @Test
    public void testRetrieveAll() {
        var result = auditService.retrieveAll();

        Assert.assertEquals(result, entities);
    }
}
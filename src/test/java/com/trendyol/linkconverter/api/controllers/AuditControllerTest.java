package com.trendyol.linkconverter.api.controllers;

import com.trendyol.linkconverter.persistence.entities.AuditEntity;
import com.trendyol.linkconverter.services.proxy.AuditService;
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
public class AuditControllerTest {

    @Spy
    @InjectMocks
    private AuditController auditController;
    @Mock
    private AuditService auditService;

    private List<AuditEntity> entities = List.of(new AuditEntity());

    @Before
    public void setup() {
        Mockito.when(auditService.retrieveAll()).thenReturn(entities);
    }

    @Test
    public void testRetrieveAll() {
        var response = auditController.retrieveAll();

        Assert.assertEquals(response.getBody(), entities);
    }

}

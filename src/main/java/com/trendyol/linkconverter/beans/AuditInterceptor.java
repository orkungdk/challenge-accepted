package com.trendyol.linkconverter.beans;

import com.trendyol.linkconverter.beans.mappers.AuditMapper;
import com.trendyol.linkconverter.persistence.repositories.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author orkungedik
 */
@Component
public class AuditInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LinkConversionSession session;
    @Autowired
    private AuditRepository auditRepository;
    @Autowired
    private AuditMapper auditMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        session.setPath(request.getServletPath());

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        var entity = auditMapper.convert(session);
        auditRepository.save(entity);

        super.afterCompletion(request, response, handler, ex);
    }
}
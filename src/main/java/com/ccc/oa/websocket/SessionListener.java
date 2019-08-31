package com.ccc.oa.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

public class SessionListener extends HttpSessionEventPublisher {
    private static final Logger LOG = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        LOG.info("新的session： " + session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        LOG.info("销毁session： " + session.getId());
    }
}

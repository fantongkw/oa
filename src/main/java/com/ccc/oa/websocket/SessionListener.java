package com.ccc.oa.websocket;

import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

public class SessionListener extends HttpSessionEventPublisher {

    private static int onlineUsers = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        onlineUsers++;
        System.out.println("新的session： " + session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        onlineUsers--;
        System.out.println("销毁session： " + session.getId());
    }

    public static int getOnlineUsers() {
        return onlineUsers;
    }

}

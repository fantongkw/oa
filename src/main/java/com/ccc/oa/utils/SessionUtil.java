package com.ccc.oa.utils;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SessionUtil {

    /**
     * @Description 判断用户是否登陆
     * @Date 2019/1/8 16:32
     * @Param [request, sessionRegistry, user]
     * @return void
     **/
    public static void deleteSameUser(HttpServletRequest request, SessionRegistry sessionRegistry, User user) {
        SecurityContext securityContext = (SecurityContext) request.getSession().getServletContext().getAttribute("SPRING_SECURITY_CONTEXT");
        List<SessionInformation> sessionInformation;
        sessionInformation = sessionRegistry.getAllSessions(securityContext.getAuthentication().getPrincipal(), true);
        String currentSessionId;
        if (sessionInformation != null && sessionInformation.size() == 0) {
            sessionRegistry.registerNewSession(request.getSession().getId(), securityContext.getAuthentication().getPrincipal());
            sessionInformation = sessionRegistry.getAllSessions(securityContext.getAuthentication().getPrincipal(), false);

        }
        currentSessionId = sessionInformation != null ? sessionInformation.get(0).getSessionId() : null;
        List<Object> objects = sessionRegistry.getAllPrincipals();
        for (Object principal: objects) {
            if (principal instanceof User && (user.getUsername().equals(((User) principal).getUsername()))) {
                List<SessionInformation> oldSessionInformation = sessionRegistry.getAllSessions(principal, false);
                if (oldSessionInformation != null && oldSessionInformation.size() > 0 && !oldSessionInformation.get(0).getSessionId().equals(currentSessionId)) {
                    if (sessionInformation != null) {
                        for (SessionInformation information : sessionInformation) {
                            information.expireNow();
                            securityContext.setAuthentication(null);
                            sessionRegistry.removeSessionInformation(currentSessionId);
                        }
                    }
                }
            }
        }
    }

    /**
     * @Description 注销掉前一个用户
     * @Date 2019/1/8 16:33
     * @Param [request, sessionRegistry, user]
     * @return void
     **/
    public static void dropPreviousUser(HttpServletRequest request, SessionRegistry sessionRegistry, User user) {
        SecurityContext securityContext = (SecurityContext) request.getSession().getServletContext().getAttribute("SPRING_SECURITY_CONTEXT");
        List<SessionInformation> sessionInformation;
        sessionInformation = sessionRegistry.getAllSessions(securityContext.getAuthentication().getPrincipal(), true);
        if (sessionInformation.size() > 0) {
            String currentSessionId = sessionInformation.get(0).getSessionId();
            List<Object> objects = sessionRegistry.getAllPrincipals();
            for (Object principal : objects) {
                if (principal instanceof User && (user.getUsername().equals(((User) principal).getUsername()))) {
                    List<SessionInformation> oldSessionInformation = sessionRegistry.getAllSessions(principal, false);
                    if (oldSessionInformation != null && oldSessionInformation.size() > 0 && !oldSessionInformation.get(0).getSessionId().equals(currentSessionId)) {
                        for (SessionInformation information : oldSessionInformation) {
                            information.expireNow();
                        }
                    }
                }
            }
        }
    }

    /**
     * @Description 使session失效
     * @Date 2019/1/8 16:33
     * @Param [request, sessionRegistry, user]
     * @return void
     **/
    public static void exipreSession(HttpServletRequest request, SessionRegistry sessionRegistry, User user) {
        List<SessionInformation> sessionInformation = null;
        if (user != null) {
            List<Object> objects = sessionRegistry.getAllPrincipals();
            for (Object principal : objects) {
                if (principal instanceof User && (user.getUsername().equals(((User) principal).getUsername()))) {
                    sessionInformation = sessionRegistry.getAllSessions(principal, false);
                }
            }
        } else if (request != null){
            SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContext.getAuthentication().getPrincipal() != null) {
                sessionInformation = sessionRegistry.getAllSessions(securityContext.getAuthentication().getPrincipal(), false);
                securityContext.setAuthentication(null);
            }
        }
        if (sessionInformation != null && sessionInformation.size() > 0) {
            for (SessionInformation information : sessionInformation) {
                information.expireNow();
                sessionRegistry.removeSessionInformation(information.getSessionId());
            }
        }
    }

}

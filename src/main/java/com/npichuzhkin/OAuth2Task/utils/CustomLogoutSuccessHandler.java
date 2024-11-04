package com.npichuzhkin.OAuth2Task.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final SecurityLogger securityLogger;

    public CustomLogoutSuccessHandler(SecurityLogger securityLogger) {
        this.securityLogger = securityLogger;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        securityLogger.onLogoutSuccess(new LogoutSuccessEvent(authentication));
        response.sendRedirect("/login");
    }
}


package com.npichuzhkin.OAuth2Task.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SecurityLogger {

    private static final Logger logger = LoggerFactory.getLogger(SecurityLogger.class);

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        logger.info("User {} authenticated successfully", event.getAuthentication().getName());
    }

    @EventListener
    public void onLogoutSuccess(LogoutSuccessEvent event) {
        logger.info("User {} logged out successfully", event.getAuthentication().getName());
    }
}

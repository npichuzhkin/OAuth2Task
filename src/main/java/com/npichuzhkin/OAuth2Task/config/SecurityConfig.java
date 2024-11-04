package com.npichuzhkin.OAuth2Task.config;

import com.npichuzhkin.OAuth2Task.utils.CustomAuthenticationSuccessHandler;
import com.npichuzhkin.OAuth2Task.utils.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> socialAppService;

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    public SecurityConfig(OAuth2UserService<OAuth2UserRequest, OAuth2User> socialAppService,
                          CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler, CustomLogoutSuccessHandler customLogoutSuccessHandler) {
        this.socialAppService = socialAppService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user").hasAnyRole("USER","ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login")
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .userService(socialAppService))
                        .successHandler(customAuthenticationSuccessHandler)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler(customLogoutSuccessHandler)
                );

        return http.build();
    }

}


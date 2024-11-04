package com.npichuzhkin.OAuth2Task.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {

    @GetMapping("/login")
    public String login() {
        return "redirect:/oauth2/authorization/github";
    }

    @GetMapping("/user")
    public String getUserInfo(Model model, @AuthenticationPrincipal OAuth2User principal) {
        getPrincipalInfo(model, principal);
        return "user";
    }

    @GetMapping("/admin")
    public String getAdminInfo(Model model, @AuthenticationPrincipal OAuth2User principal) {
        getPrincipalInfo(model, principal);
        return "admin";
    }

    private void getPrincipalInfo(Model model, OAuth2User principal){
        model.addAttribute("name", principal.getAttribute("name"));
        model.addAttribute("login", principal.getAttribute("login"));
        model.addAttribute("id", principal.getAttribute("id"));
        model.addAttribute("html_url", principal.getAttribute("html_url"));
    }
}


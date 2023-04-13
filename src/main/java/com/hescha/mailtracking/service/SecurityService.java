package com.hescha.mailtracking.service;

import com.hescha.mailtracking.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;

    public User getLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            return null;
        }
        return userService.findByUsername(auth.getName());
    }
}

package com.ega.bank.service.impl;

import com.ega.bank.service.UserService;
import com.ega.bank.tools.Helper;
import com.ega.bank.user.User;
import com.ega.bank.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Helper helper;

    @Override
    public String getUserName(HttpServletRequest request) {
        System.out.println(helper.getUserNameFromRequest(request));
        return helper.getUserNameFromRequest(request);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow();
    }
}

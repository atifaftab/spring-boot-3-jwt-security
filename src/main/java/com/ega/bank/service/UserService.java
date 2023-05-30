package com.ega.bank.service;

import com.ega.bank.user.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface UserService {

    String getUserName(HttpServletRequest request);

    User findByEmail(String email);
}

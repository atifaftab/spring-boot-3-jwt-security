package com.ega.bank.tools;

import com.ega.bank.config.JwtService;
import com.ega.bank.user.User;
import com.ega.bank.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Random;

@RequiredArgsConstructor
@Component
public class Helper {

    private final JwtService jwtService;
    private final UserRepository repository;

    //get 10 digit random number for account
    public static String getTenDigitAccountNumber(){
        Random random = new Random();
        long randomNum = (long) (random.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        String formattedNum = String.format("%010d", randomNum);
        return formattedNum;
    }

    public int getUserIdFromRequest(HttpServletRequest request){
       User user = getUserFromRequest(request);
       return user.getId();
    }

    public String  getUserNameFromRequest(HttpServletRequest request){
        User user = getUserFromRequest(request);
        return user.getFirstname() +" "+ user.getLastname();
    }

    private User getUserFromRequest(HttpServletRequest request){
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        User user = new User();
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            user = this.repository.findByEmail(userEmail)
                    .orElseThrow(() -> new DataAccessResourceFailureException("No User found"));
        }
        return user;
    }
}

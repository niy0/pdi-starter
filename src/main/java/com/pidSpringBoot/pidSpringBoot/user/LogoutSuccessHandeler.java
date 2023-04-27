package com.pidSpringBoot.pidSpringBoot.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LogoutSuccessHandeler extends SimpleUrlLogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        /**String userLoginName = customUserDetails.getLoginUser();
        customUserDetailsService.loadUserByUsername(userLoginName);//a tester
        super.onLogoutSuccess(request, response, authentication);**/

        String userLogoutName = customUserDetails.getFullName();
        System.out.println("L'utilisateur" + userLogoutName + "est bien déconnecté");
        response.sendRedirect(request.getContextPath());

    }
}

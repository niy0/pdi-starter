package com.pidSpringBoot.pidSpringBoot.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        System.out.println("Nom :"+ customUserDetails.getFullName());

        String redirecturl = request.getContextPath();
        if( customUserDetails.hasRole("admin")) {

            redirecturl += "/admin_home";
        } else if( customUserDetails.hasRole("member")) {

            redirecturl += "/member_home";
        }else {
            redirecturl += "/";
        }
        response.sendRedirect(redirecturl);
    }
}

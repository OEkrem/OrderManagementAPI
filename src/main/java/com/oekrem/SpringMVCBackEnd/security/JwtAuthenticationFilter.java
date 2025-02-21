package com.oekrem.SpringMVCBackEnd.security;

import com.oekrem.SpringMVCBackEnd.services.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal
            (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try{
            String bearerToken = request.getHeader("Authorization");
            String token = null;
            if(bearerToken != null && bearerToken.startsWith("Bearer ")){
                token = bearerToken.substring(7);
            }
            if (token != null) {
                UserDetails userDetails = authenticationService.validateToken(token);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                if(userDetails instanceof OrderUserDetails){
                    request.setAttribute("userId", ((OrderUserDetails) userDetails).getId());
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            log.warn("Received invalid JWT token: ", e);
        }

        filterChain.doFilter(request, response);
    }
}

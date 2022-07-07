package com.unnamedteam.stuffrent.filters;

import com.unnamedteam.stuffrent.filters.jwt.JwtProvider;
import com.unnamedteam.stuffrent.model.security.UserDetailsServiceImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.unnamedteam.stuffrent.model.SecurityConstants.HEADER_STRING;
import static com.unnamedteam.stuffrent.model.SecurityConstants.TOKEN_PREFIX;


@Component
@Log
public class JwtFilter extends GenericFilterBean {

    private JwtProvider jwtProvider;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public JwtFilter(JwtProvider jwtProvider, UserDetailsServiceImpl userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        logger.info(JwtFilter.class + " do filter");
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null && jwtProvider.validateToken(token)) {
            String username = jwtProvider.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(HEADER_STRING);
        if (StringUtils.hasText(bearer) && bearer.startsWith(TOKEN_PREFIX)) {
            return bearer.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}

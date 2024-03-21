package com.easyapp.adm.mathias.security;

import com.easyapp.adm.mathias.MathiasApplication;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class FirebaseFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> token = recoveryToken(request);
        if (token.isPresent()) {
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token.get());
                var auth = new UsernamePasswordAuthenticationToken(
                        decodedToken.getUid(),
                        null,
                        null
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (FirebaseAuthException e) {
                LoggerFactory.getLogger(MathiasApplication.class).info(e.toString());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
            LoggerFactory.getLogger(MathiasApplication.class).info("SSSSSSSSSSSSSsss");
        }
        filterChain.doFilter(request, response);
    }

    private static Optional<String> recoveryToken(HttpServletRequest request){
        String auth = request.getHeader("Authorization");
        if(auth==null) return Optional.empty();
        return Optional.of(auth.replaceAll("Bearer", "").trim());
    }
}


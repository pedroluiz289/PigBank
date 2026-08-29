package com.example.demo.config.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AcessoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        String tipo = (String) session.getAttribute("tipo");

        if (uri.startsWith("/corrente") && !tipo.equals("corrente")) {
            response.sendRedirect("/");
            return false;
        }

        if (uri.startsWith("/poupanca") && !tipo.equals("poupanca")) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}

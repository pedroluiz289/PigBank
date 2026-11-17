package projetos.pigbank.config;

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

        if (tipo == null) {
            response.sendRedirect("/");
            return false;
        }

        if ("corrente".equalsIgnoreCase(tipo) && uri.startsWith("/poupanca")) {
            response.sendRedirect("/");
            return false;
        }

        if ("poupanca".equalsIgnoreCase(tipo) && uri.startsWith("/corrente")) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}

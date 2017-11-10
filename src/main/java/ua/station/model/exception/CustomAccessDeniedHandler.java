package ua.station.model.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by sa on 07.11.17.
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private static String REALM="Restful Spring Security Application";

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException,
            ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + REALM + "\"");
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 403 : " + accessDeniedException.getMessage());

    }

}
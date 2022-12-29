package com.bside.server.global.error;


import com.bside.server.global.error.exception.AuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 필터에서 발생한 exception 처리
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (RequestRejectedException e) {
            String message = e.getMessage();
            ErrorResponse errorResponse = new ErrorResponse(ErrorCode.REQUEST_REJECTED.getCode(), message);
            sendErrorResponse(response, errorResponse, HttpStatus.BAD_REQUEST);
        } catch (AuthenticationException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
            sendErrorResponse(response, errorResponse, HttpStatus.UNAUTHORIZED);
        } catch (JwtException e) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_TOKEN);
            sendErrorResponse(response, errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    public void sendErrorResponse(HttpServletResponse httpResponse, ErrorResponse errorResponse, HttpStatus status) throws IOException {
        httpResponse.setStatus(status.value());
        httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpResponse.setCharacterEncoding("utf-8");
        httpResponse.getWriter().print(objectMapper.writeValueAsString(errorResponse));
    }
}

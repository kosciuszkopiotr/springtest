package com.visionmate.springtest.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visionmate.springtest.api.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO()
                    .withMessage(e.getMessage())
                    .withStatusCode(HttpStatus.PRECONDITION_FAILED.value());

            response.setStatus(HttpStatus.PRECONDITION_FAILED.value());
            response.getWriter().write(convertObjectToJson(errorResponse));
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}

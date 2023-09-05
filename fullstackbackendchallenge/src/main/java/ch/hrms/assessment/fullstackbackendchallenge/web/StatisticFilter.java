package ch.hrms.assessment.fullstackbackendchallenge.web;

import ch.hrms.assessment.fullstackbackendchallenge.api.ErrorMessage;
import ch.hrms.assessment.fullstackbackendchallenge.service.StatisticService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatisticFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final StatisticService service;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        doFilterInternal(new ContentCachingRequestWrapper(request),
                new ContentCachingResponseWrapper(response),
                filterChain);
    }

    void doFilterInternal(ContentCachingRequestWrapper request,
                          ContentCachingResponseWrapper response,
                          FilterChain filterChain) throws ServletException, IOException {

        filterChain.doFilter(request, response);

        if (HttpStatus.OK.value() == response.getStatus()) {

            service.incrementValidRequestCount(request.getMethod());

        } else {

            byte[] responseBody = response.getContentAsByteArray();
            ErrorMessage message = getErrorMessage(responseBody);

            service.addWrongRequestMessage(request.getMethod(), message.getDetail());
        }

        // Finally remember to respond to the client with the cached data.
        response.copyBodyToResponse();
    }

    //{"type":"about:blank","title":"Bad Request","status":400,"detail":"Invalid request content.","instance":"/fullstack-be-challenge/api/persons/"}
    ErrorMessage getErrorMessage(byte[] content) {
        try {
            return objectMapper.readValue(content, ErrorMessage.class);

        } catch (Exception e) {

            ErrorMessage message = new ErrorMessage();
            message.setTitle(e.getClass().getSimpleName());
            message.setDetail(e.getMessage());
            return message;
        }
    }
}

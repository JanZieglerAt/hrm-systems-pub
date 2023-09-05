package ch.hrms.assessment.fullstackbackendchallenge.web;

import ch.hrms.assessment.fullstackbackendchallenge.api.ErrorMessage;
import ch.hrms.assessment.fullstackbackendchallenge.service.StatisticService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class StatisticFilterTest {
    @MockBean
    private StatisticService service;
    @MockBean
    private ObjectMapper objectMapper;
    @SpyBean
    private StatisticFilter filter;

    @Test
    void doFilterInternalTest() throws ServletException, IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        doNothing().when(filter).doFilterInternal(any(ContentCachingRequestWrapper.class),
                any(ContentCachingResponseWrapper.class),
                eq(filterChain));

        filter.doFilterInternal(request, response, filterChain);

        verify(filter).doFilterInternal(any(ContentCachingRequestWrapper.class),
                any(ContentCachingResponseWrapper.class),
                eq(filterChain));
    }

    @Test
    void doFilterInternalOK() throws ServletException, IOException {

        String method = "POST";

        ContentCachingRequestWrapper request = mock(ContentCachingRequestWrapper.class);
        ContentCachingResponseWrapper response = mock(ContentCachingResponseWrapper.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getMethod()).thenReturn(method);
        when(response.getStatus()).thenReturn(HttpStatus.OK.value());

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verify(service).incrementValidRequestCount(method);
        verify(response).copyBodyToResponse();
    }

    @Test
    void doFilterInternalNOK() throws ServletException, IOException {

        String method = "POST";
        String messageDetail = "Message detail";

        ContentCachingRequestWrapper request = mock(ContentCachingRequestWrapper.class);
        ContentCachingResponseWrapper response = mock(ContentCachingResponseWrapper.class);
        FilterChain filterChain = mock(FilterChain.class);

        ErrorMessage errorMessage = mock(ErrorMessage.class);
        byte[] responseBody = new byte[1];

        when(request.getMethod()).thenReturn(method);
        when(response.getStatus()).thenReturn(HttpStatus.BAD_REQUEST.value());
        when(response.getContentAsByteArray()).thenReturn(responseBody);
        when(errorMessage.getDetail()).thenReturn(messageDetail);
        doReturn(errorMessage).when(filter).getErrorMessage(responseBody);

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verify(filter).getErrorMessage(responseBody);
        verify(service).addWrongRequestMessage(method, messageDetail);
        verify(response).copyBodyToResponse();
    }
}


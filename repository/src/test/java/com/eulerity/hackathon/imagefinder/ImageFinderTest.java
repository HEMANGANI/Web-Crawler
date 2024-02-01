package com.eulerity.hackathon.imagefinder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ImageFinderTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter stringWriter;
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(printWriter);
        Mockito.when(request.getRequestURI()).thenReturn("/foo/foo/foo");
        Mockito.when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/foo/foo/foo"));
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testValidUrl() throws IOException, ServletException {
        // URL parameter
        String testUrl = "https://www.walmart.com/"; 
        Mockito.when(request.getParameter("url")).thenReturn(testUrl);

        // Call the servlet method
        new ImageFinder().doPost(request, response);

        // Check if the response is not empty
        Assert.assertFalse("Response should not be empty", stringWriter.toString().isEmpty());
    }

}

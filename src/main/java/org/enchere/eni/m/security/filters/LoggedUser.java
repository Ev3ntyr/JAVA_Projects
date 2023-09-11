package org.enchere.eni.m.security.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.http.HttpTimeoutException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class LoggedUser extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		
		String accessedURL = httpRequest.getServletPath();
		System.out.println(accessedURL);
		
		if (session.getAttribute("idUser") != null 
			|| accessedURL.toLowerCase().contains("home") 
			|| accessedURL.toLowerCase().contains("error")
			|| accessedURL.toLowerCase().contains("resources")
			|| accessedURL.toLowerCase().contains("...")
			|| accessedURL.toLowerCase().contains("connection")
			|| accessedURL.toLowerCase().contains("creation")) {
			chain.doFilter(request, response);
		} else {
			System.out.println("filtered");
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

	}


}

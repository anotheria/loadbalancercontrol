package net.anotheria.loadbalancercontrol.simplefilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.OutputStream;

@WebFilter(urlPatterns = "/loadbalancerSimpleFilter")
public class LoadbalancerControlFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		OutputStream outputStream = servletResponse.getOutputStream();
		outputStream.write("UP".getBytes());
		outputStream.close();

	}

	@Override
	public void destroy() {

	}
}

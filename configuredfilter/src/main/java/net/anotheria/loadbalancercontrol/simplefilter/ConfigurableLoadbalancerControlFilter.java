package net.anotheria.loadbalancercontrol.simplefilter;

import org.configureme.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.OutputStream;

@WebFilter(urlPatterns = "/loadbalancerConfiguredFilter")
public class ConfigurableLoadbalancerControlFilter implements Filter {

	private LoadbalancerConfig config = new LoadbalancerConfig();
	private static Logger log = LoggerFactory.getLogger(ConfigurableLoadbalancerControlFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try{
			ConfigurationManager.INSTANCE.configure(config);
		}catch(IllegalArgumentException e){
	   		log.error("loadbalancer.json wasn't found.");
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		String retValue = config.isSiteAvailable() ?
				"UP" : "DOWN";
		OutputStream outputStream = servletResponse.getOutputStream();
		outputStream.write(retValue.getBytes());
		outputStream.close();

	}

	@Override
	public void destroy() {

	}
}

package net.anotheria.loadbalancercontrol.simplefilter;

import net.anotheria.moskito.core.threshold.ThresholdRepository;
import net.anotheria.moskito.core.threshold.ThresholdStatus;
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

@WebFilter(urlPatterns = "/loadbalancerHealthbasedFilter")
public class HealthbasedLoadbalancerControlFilter implements Filter {

	/**
	 * Instance of the threshold repository for lookup of the underlying status and thresholds.
	 */
	private ThresholdRepository repository;
	/**
	 * Logger.
	 */
	private static Logger log = LoggerFactory.getLogger(HealthbasedLoadbalancerControlFilter.class);




	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		repository = ThresholdRepository.getInstance();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		ThresholdStatus status = repository.getWorstStatus();
		String retValue = null;
		switch(status){
			case GREEN:
			case YELLOW:
			case ORANGE:
				retValue = "UP";
				break;

			case RED:
			case PURPLE:
			case OFF:
			default:
				retValue = "DOWN";
		}
		OutputStream outputStream = servletResponse.getOutputStream();
		outputStream.write(retValue.getBytes());
		outputStream.close();

	}

	@Override
	public void destroy() {

	}
}

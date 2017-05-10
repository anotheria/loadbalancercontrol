package net.anotheria.loadbalancercontrol.simplefilter;

import org.configureme.annotations.AfterConfiguration;
import org.configureme.annotations.Configure;
import org.configureme.annotations.ConfigureMe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ConfigureMe (watch = true, name = "loadbalancer")
public class LoadbalancerConfig {

	private static Logger log = LoggerFactory.getLogger(LoadbalancerConfig.class);

	@Configure
	private boolean siteAvailable = false;

	public boolean isSiteAvailable() {
		return siteAvailable;
	}

	public void setSiteAvailable(boolean siteAvailable) {
		this.siteAvailable = siteAvailable;
	}

	@Override
	public String toString() {
		return "LoadbalancerConfig{" +
				"siteAvailable=" + siteAvailable +
				'}';
	}

	@AfterConfiguration public void debugOut(){
		log.info("New loadbalancer config is "+this);
	}
}

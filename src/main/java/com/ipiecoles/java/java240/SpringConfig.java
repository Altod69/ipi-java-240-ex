package com.ipiecoles.java.java240;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.ipiecoles.java.java240")
public class SpringConfig {

	@Value("${bitcoinService.forceRefresh}")
	Boolean forceRefresh;
	
	@Bean(name = "bitcoinServiceWithoutCache")
	@Scope("singleton")
	public BitcoinService bitcoinServiceWithoutCache() {
		BitcoinService bitcoinService = new BitcoinService();
		bitcoinService.setForceRefresh(true);
		return bitcoinService;
	}

	@Bean(name = "bitcoinServiceWithCache")
	public BitcoinService bitcoinServiceWithCache() {
		BitcoinService bitcoinService = new BitcoinService();
		bitcoinService.setForceRefresh(false);
		return bitcoinService;
	}
}

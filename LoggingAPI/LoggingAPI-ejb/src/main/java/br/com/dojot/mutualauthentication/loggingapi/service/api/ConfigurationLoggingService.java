package br.com.dojot.mutualauthentication.loggingapi.service.api;

import java.util.List;

import br.com.dojot.mutualauthentication.loggingapi.beans.to.ConfigurationLoggingTO;

public interface ConfigurationLoggingService {
	
	ConfigurationLoggingTO findByConfiguration(String configuration);

	List<ConfigurationLoggingTO> search();

    ConfigurationLoggingTO update(ConfigurationLoggingTO to);
}

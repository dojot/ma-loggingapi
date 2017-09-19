package br.com.cpqd.mutualauthentication.loggingapi.facade.api;

import java.util.List;

import javax.ejb.Local;

import br.com.cpqd.mutualauthentication.loggingapi.beans.to.ConfigurationLoggingTO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.FiltersTO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.LoggingGeneratorTO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.LoggingTO;

@Local
public interface LoggingFacade {
	
	void logGenerator(LoggingGeneratorTO log);
	
	List<LoggingTO> searchLoggingFilters(FiltersTO filters);
	
	List<LoggingTO> searchIndexedLogFilters(FiltersTO filters);
	
    List<ConfigurationLoggingTO> searchConfigurationLoggingTO();
    
    ConfigurationLoggingTO updateConfigurationLoggingTO(ConfigurationLoggingTO to);
    
    String findConfigurationByKey(String key);
}
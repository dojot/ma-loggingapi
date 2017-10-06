package br.com.dojot.mutualauthentication.loggingapi.service.api;

import java.util.List;

import javax.ejb.Local;

import br.com.dojot.mutualauthentication.loggingapi.beans.to.FiltersTO;
import br.com.dojot.mutualauthentication.loggingapi.beans.to.LoggingGeneratorTO;
import br.com.dojot.mutualauthentication.loggingapi.beans.to.LoggingTO;

@Local
public interface LoggingService {
	
	public enum Level { INFO, WARNING, ERROR };
	
	void saveLogging(Level level, String component, String username, String details);
	
	void logGenerator(LoggingGeneratorTO log);
	
	void log(String logging);
	
	List<LoggingTO> searchLoggingFilters(FiltersTO filters);
	
}

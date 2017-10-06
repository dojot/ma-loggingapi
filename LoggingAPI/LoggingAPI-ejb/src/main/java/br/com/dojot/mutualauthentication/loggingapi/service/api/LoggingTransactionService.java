package br.com.dojot.mutualauthentication.loggingapi.service.api;

import java.util.List;

import javax.ejb.Local;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.LoggingTransactionVO;
import br.com.dojot.mutualauthentication.loggingapi.beans.to.FiltersTO;
import br.com.dojot.mutualauthentication.loggingapi.beans.to.LoggingTO;

@Local
public interface LoggingTransactionService {

	void log(String logging);
	
	List<LoggingTO> searchLoggingTransactionFilters(FiltersTO filters);
}

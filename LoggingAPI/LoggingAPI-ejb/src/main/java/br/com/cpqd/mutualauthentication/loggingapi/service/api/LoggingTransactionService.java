package br.com.cpqd.mutualauthentication.loggingapi.service.api;

import java.util.List;

import javax.ejb.Local;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.LoggingTransactionVO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.FiltersTO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.LoggingTO;

@Local
public interface LoggingTransactionService {

	void log(String logging);
	
	List<LoggingTO> searchLoggingTransactionFilters(FiltersTO filters);
}

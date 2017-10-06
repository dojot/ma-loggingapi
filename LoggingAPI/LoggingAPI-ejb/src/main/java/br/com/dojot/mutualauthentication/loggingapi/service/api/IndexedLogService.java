package br.com.dojot.mutualauthentication.loggingapi.service.api;

import java.util.List;

import javax.ejb.Local;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.LoggingVO;
import br.com.dojot.mutualauthentication.loggingapi.beans.to.FiltersTO;
import br.com.dojot.mutualauthentication.loggingapi.beans.to.LoggingTO;

@Local
public interface IndexedLogService {
	
	void save(LoggingVO vo);

	List<LoggingTO> searchFilter(FiltersTO filters);
}

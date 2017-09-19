package br.com.cpqd.mutualauthentication.loggingapi.service.api;

import java.util.List;

import javax.ejb.Local;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.LoggingVO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.FiltersTO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.LoggingTO;

@Local
public interface IndexedLogService {
	
	void save(LoggingVO vo);

	List<LoggingTO> searchFilter(FiltersTO filters);
}

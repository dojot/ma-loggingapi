package br.com.dojot.mutualauthentication.loggingapi.dao.api;

import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.LoggingVO;

public interface IndexedLogDAO {

	void save(Long id, String json);
	
	HttpEntity searchLevel(String level);
	
	HttpEntity searchFieldIndex(String fields , String value);
	
	List<LoggingVO> searchLoggingPeriod(Date init, Date end);
}

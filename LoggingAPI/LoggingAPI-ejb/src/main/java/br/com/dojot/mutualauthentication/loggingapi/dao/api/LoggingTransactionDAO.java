package br.com.dojot.mutualauthentication.loggingapi.dao.api;

import java.util.Date;
import java.util.List;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.LoggingTransactionVO;

public interface LoggingTransactionDAO {

	void add(LoggingTransactionVO vo);
	
	List<LoggingTransactionVO> searchLoggingTransaction(String transaction);
	
	List<LoggingTransactionVO> searchLoggingTransactionPeriod(Date init, Date end);
}

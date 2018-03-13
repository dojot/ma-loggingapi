package br.com.dojot.mutualauthentication.loggingapi.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.LoggingTransactionVO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.LoggingTransactionDAO;
import br.com.dojot.mutualauthentication.loggingapi.service.api.ConfigService;

@Singleton
public class LoggingTransactionDAOImpl implements LoggingTransactionDAO {

	@EJB
	private ConfigService configService;

	@PostConstruct
	public void init() {
	}

	@PreDestroy
	public void close() {
	}

	@Override
	public void add(LoggingTransactionVO vo) {
	}

	@Override
	public List<LoggingTransactionVO> searchLoggingTransaction(String transaction) {
		List<LoggingTransactionVO> logs = new ArrayList<LoggingTransactionVO>();
		return logs;
	}

	@Override
	public List<LoggingTransactionVO> searchLoggingTransactionPeriod(Date init, Date end) {
		List<LoggingTransactionVO> logs = new ArrayList<LoggingTransactionVO>();
		return logs;
	}

}

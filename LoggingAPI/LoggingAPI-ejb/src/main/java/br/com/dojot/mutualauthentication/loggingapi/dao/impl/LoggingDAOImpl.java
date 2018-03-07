package br.com.dojot.mutualauthentication.loggingapi.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;


import br.com.dojot.mutualauthentication.loggingapi.beans.entity.LoggingVO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.LoggingDAO;

@Singleton
public class LoggingDAOImpl implements LoggingDAO {

	@Override
	public void save(LoggingVO vo) {
	}

	@Override
	public List<LoggingVO> searchLoggingToClear(Date date) {
		return new ArrayList<LoggingVO>();
	}

	@Override
	public List<LoggingVO> searchLoggingPeriod(Date init, Date end) {
		return new ArrayList<LoggingVO>();
	}

}

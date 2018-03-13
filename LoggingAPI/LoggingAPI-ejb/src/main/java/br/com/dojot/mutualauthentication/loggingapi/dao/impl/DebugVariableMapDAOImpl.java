package br.com.dojot.mutualauthentication.loggingapi.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.DebugVariableMapVO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.DebugVariableMapDAO;
import br.com.dojot.mutualauthentication.loggingapi.service.api.ConfigService;

@Singleton
public class DebugVariableMapDAOImpl implements DebugVariableMapDAO {

	@EJB
	private ConfigService configService;

	@PostConstruct
	public void init() {
	}

	@PreDestroy
	public void close() {
	}

	@Override
	public void save(DebugVariableMapVO vo) {
	}

	@Override
	public void remove(String transactionId) {
	}
}

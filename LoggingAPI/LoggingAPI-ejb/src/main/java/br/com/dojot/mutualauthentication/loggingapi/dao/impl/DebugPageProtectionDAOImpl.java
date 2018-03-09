package br.com.dojot.mutualauthentication.loggingapi.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.DebugPageProtectionVO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.DebugPageProtectionDAO;
import br.com.dojot.mutualauthentication.loggingapi.service.api.ConfigService;

@Singleton
public class DebugPageProtectionDAOImpl implements DebugPageProtectionDAO {

	@EJB
	private ConfigService configService;

	@PostConstruct
	public void init() {
	}

	@PreDestroy
	public void close() {
	}

	@Override
	public void save(DebugPageProtectionVO vo) {
	}

	@Override
	public void remove(String transactionId) {
	}
}

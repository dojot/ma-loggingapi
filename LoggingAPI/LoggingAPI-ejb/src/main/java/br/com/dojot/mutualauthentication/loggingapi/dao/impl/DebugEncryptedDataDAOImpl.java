package br.com.dojot.mutualauthentication.loggingapi.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.DebugEncryptedDataVO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.DebugEncryptedDataDAO;
import br.com.dojot.mutualauthentication.loggingapi.service.api.ConfigService;

@Singleton
public class DebugEncryptedDataDAOImpl implements DebugEncryptedDataDAO {

	@EJB
	private ConfigService configService;

	@PostConstruct
	public void init() {
	}

	@PreDestroy
	public void close() {
	}

	@Override
	public void save(DebugEncryptedDataVO vo) {
	}

	@Override
	public void remove(String transactionId) {
	}
}

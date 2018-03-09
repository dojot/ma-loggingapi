package br.com.dojot.mutualauthentication.loggingapi.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.DebugTransactionVO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.DebugTransactionDAO;
import br.com.dojot.mutualauthentication.loggingapi.service.api.ConfigService;

@Singleton
public class DebugTransactionDAOImpl implements DebugTransactionDAO {

	@EJB
	private ConfigService configService;

	@PostConstruct
	public void init() {
	}

	@PreDestroy
	public void close() {
	}

	@Override
	public void save(DebugTransactionVO vo) {
	}

}

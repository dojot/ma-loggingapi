package br.com.dojot.mutualauthentication.loggingapi.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.DebugCryptoChannelVO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.DebugCryptoChannelDAO;
import br.com.dojot.mutualauthentication.loggingapi.service.api.ConfigService;

@Singleton
public class DebugCryptoChannelDAOImpl implements DebugCryptoChannelDAO {

	@EJB
	private ConfigService configService;

	@PostConstruct
	public void init() {
	}

	@PreDestroy
	public void close() {
	}

	@Override
	public void save(DebugCryptoChannelVO vo) {
	}

	@Override
	public void remove(String transactionId) {
	}
}

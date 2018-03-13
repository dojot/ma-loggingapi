package br.com.dojot.mutualauthentication.loggingapi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.ConfigurationLoggingVO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.ConfigurationLoggingDAO;
import br.com.dojot.mutualauthentication.loggingapi.service.api.ConfigService;

@Singleton
public class ConfigurationLoggingDAOImpl implements ConfigurationLoggingDAO {

	@EJB
	private ConfigService configService;

	@PostConstruct
	public void init() {
	}

	@PreDestroy
	public void close() {
	}

	@Override
	public List<ConfigurationLoggingVO> search() {
		List<ConfigurationLoggingVO> logs = new ArrayList<ConfigurationLoggingVO>();
		return logs;
	}

	@Override
	public ConfigurationLoggingVO findByConfiguration(String configuration) {
		ConfigurationLoggingVO config = null;
		return config;
	}

	@Override
	public void update(String id, String result) {
	}

}

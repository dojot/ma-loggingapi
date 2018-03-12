package br.com.dojot.mutualauthentication.loggingapi.dao.impl;

import java.util.List;

import javax.ejb.Singleton;

import br.com.dojot.mutualauthentication.loggingapi.dao.api.ConfigDAO;

@Singleton
public class ConfigDAOImpl extends GenericRedisDAOImpl implements ConfigDAO {

	@Override
	public String findConfigurationByKey(String key) {
		return super.get(key);
	}

	@Override
	public List<String> searchCassandraContactPoints() {
		return null;
	}


	@Override
	public List<String> searchElasticSearchNodes() {
		return null;
	}

}

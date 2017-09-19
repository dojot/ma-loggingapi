package br.com.cpqd.mutualauthentication.loggingapi.dao.impl;

import java.util.List;

import javax.ejb.Singleton;

import br.com.cpqd.mutualauthentication.loggingapi.dao.api.ConfigDAO;

@Singleton
public class ConfigDAOImpl extends GenericRedisDAOImpl implements ConfigDAO {
	
	private String CASSANDRA_KEYS = "CASSANDRA_CONTACT_POINTS";
	
	private String ELASTIC_SEARCH_NODES = "ELASTIC_SEARCH_NODES";

	@Override
	public String findConfigurationByKey(String key) {
		return super.get(key);
	}

	@Override
	public List<String> searchCassandraContactPoints() {
		return super.lrange(CASSANDRA_KEYS);
	}
	

	@Override
	public List<String> searchElasticSearchNodes() {
		return super.lrange(ELASTIC_SEARCH_NODES);
	}  
	
}

package br.com.cpqd.mutualauthentication.loggingapi.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cpqd.mutualauthentication.loggingapi.dao.api.ConfigDAO;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.ConfigService;

@Stateless
public class ConfigServiceImpl implements ConfigService {
	
	@EJB
	private ConfigDAO configDAO;

	@Override
	public String findConfigurationByKey(String key) {
		return configDAO.findConfigurationByKey(key);
	}

	@Override
	public String[] findCassandraContactPoints() {
		List<String> hosts = configDAO.searchCassandraContactPoints();
		int size = hosts.size();
		String[] contactPoints = new String[size];		
		for (String host : hosts) {
			contactPoints[size-1] = host;
			size--;
		}
		return contactPoints;
	}	

	@Override
	public List<String> searchElasticSearchNodes() {
		return configDAO.searchElasticSearchNodes();
	}		

}

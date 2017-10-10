package br.com.dojot.mutualauthentication.loggingapi.service.api;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ConfigService {
	
	String findConfigurationByKey(String key);
	
	String[] findCassandraContactPoints();
	
	List<String> searchElasticSearchNodes();
}
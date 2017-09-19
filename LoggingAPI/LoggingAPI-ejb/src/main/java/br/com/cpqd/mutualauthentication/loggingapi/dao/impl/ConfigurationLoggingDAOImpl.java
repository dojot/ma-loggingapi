package br.com.cpqd.mutualauthentication.loggingapi.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.ConfigurationLoggingVO;
import br.com.cpqd.mutualauthentication.loggingapi.dao.api.ConfigurationLoggingDAO;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.ConfigService;
import br.com.cpqd.mutualauthentication.loggingapi.utils.LoggingConstants;

@Singleton
public class ConfigurationLoggingDAOImpl implements ConfigurationLoggingDAO {

	private Cluster cluster;
	
	private Session session;
	
	private Map<String, PreparedStatement> queries;
	
	@EJB
	private ConfigService configService;
	
	@PostConstruct
	public void init() {
		cluster = Cluster
	            .builder()
	            .addContactPoints(configService.findCassandraContactPoints())
	            .withRetryPolicy(DefaultRetryPolicy.INSTANCE)
	            .withLoadBalancingPolicy(
	                    new TokenAwarePolicy(new DCAwareRoundRobinPolicy.Builder().build()))
	            .build();     		
		session = cluster.connect(LoggingConstants.CASSANDRA_KEYSPACE_LOGGING);
		
		queries = new HashMap<>();
		queries.put("search", session.prepare("SELECT * FROM configurationlogging"));
		queries.put("find", session.prepare("SELECT * FROM configurationlogging WHERE configuration = :configuration ALLOW FILTERING"));
		queries.put("update", session.prepare("UPDATE configurationlogging SET result = :result WHERE id = :id"));
	}
		
	@PreDestroy
	public void close() {
		session.close();
		cluster.close();
	}

	@Override
	public List<ConfigurationLoggingVO> search() {
		List<ConfigurationLoggingVO> logs = new ArrayList<ConfigurationLoggingVO>();
		try {
			ResultSet results = session.execute(queries.get("search").bind());
			for (Row row : results) {
				ConfigurationLoggingVO vo = new ConfigurationLoggingVO();
				vo.setId(row.getString("id"));
				vo.setConfiguration(row.getString("configuration"));
				vo.setDescription(row.getString("description"));
				vo.setResult(row.getString("result"));
				logs.add(vo);
			}
		} catch (Exception ex) {
			System.out.println("Erro ao processar a query: " + ex.getMessage());
		}
		return logs;
	}

	@Override
	public ConfigurationLoggingVO findByConfiguration(String configuration) {
		ConfigurationLoggingVO config = null;
		try {
			ResultSet results = session.execute(queries.get("find").bind().setString("configuration", configuration));
			Row row = results.one();
			if (row != null) {
				config = new ConfigurationLoggingVO();			
				config.setId(row.getString("id"));
				config.setConfiguration(row.getString("configuration"));
				config.setDescription(row.getString("description"));
				config.setResult(row.getString("result"));
			}
		} catch (Exception ex) {
			System.out.println("Erro ao processar a query: " + ex.getMessage());
		}
		return config;
	}

	@Override
	public void update(String id, String result) {
		try {
			session.execute(queries.get("update").bind()
					.setString("result", result)
					.setString("id", id));
		} catch (Exception ex) {
			System.out.println("Erro ao atualizar um configurationlogging: " + ex.getMessage());
		}		
	}	
	
}


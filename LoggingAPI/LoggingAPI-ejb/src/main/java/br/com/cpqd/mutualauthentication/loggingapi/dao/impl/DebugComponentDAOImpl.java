package br.com.cpqd.mutualauthentication.loggingapi.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.DebugComponentVO;
import br.com.cpqd.mutualauthentication.loggingapi.dao.api.DebugComponentDAO;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.ConfigService;
import br.com.cpqd.mutualauthentication.loggingapi.utils.LoggingConstants;

@Singleton
public class DebugComponentDAOImpl implements DebugComponentDAO { 

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
		queries.put("save", session.prepare("INSERT INTO debugcomponent (transactionId, fieldSizes, preobfuscationParam, date) VALUES (:transactionId, :fieldSizes, :preobfuscationParam, :date)"));
		queries.put("remove", session.prepare("DELETE FROM debugcomponent WHERE transactionId = :transactionId"));
	}
		
	@PreDestroy
	public void close() {
		session.close();
		cluster.close();
	}
	
	@Override
	public void save(DebugComponentVO vo) {
		try {
			session.execute(queries.get("save").bind()
					.setString("fieldSizes", vo.getFieldSizes())
					.setString("preobfuscationParam", vo.getPreobfuscationParam())
					.setString("transactionId", vo.getTransactionId())
					.setTimestamp("date", vo.getDate()));
		} catch (Exception ex) {
			System.out.println("Erro ao persisir um debugcomponent: " + ex.getMessage());
		}		
	}

	@Override
	public void remove(String transactionId) {
		try {
			session.execute(queries.get("remove").bind().setString("transactionId", transactionId));
		} catch (Exception ex) {
			System.out.println("Erro ao excluir um debugcomponent: " + ex.getMessage());
		}
	}	
}

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

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.DebugTransactionVO;
import br.com.cpqd.mutualauthentication.loggingapi.dao.api.DebugTransactionDAO;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.ConfigService;
import br.com.cpqd.mutualauthentication.loggingapi.utils.LoggingConstants;

@Singleton
public class DebugTransactionDAOImpl implements DebugTransactionDAO { 

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
		queries.put("save", session.prepare("INSERT INTO debugtransaction (client, component, dateClient, session, transactionId, type, date) VALUES (:client, :component, :dateClient, :session, :transactionId, :type, :date)"));
	}
		
	@PreDestroy
	public void close() {
		session.close();
		cluster.close();
	}
	
	@Override
	public void save(DebugTransactionVO vo) {
		try {
			session.execute(queries.get("save").bind()
					.setString("client", vo.getClient())
					.setString("component", vo.getComponent())
					.setString("dateClient", vo.getDateClient())
					.setString("session", vo.getSession())
					.setString("transactionId", vo.getTransaction())
					.setString("type", vo.getType())
					.setTimestamp("date", vo.getDate()));
		} catch (Exception ex) {
			System.out.println("Erro ao persisir um debugtransaction: " + ex.getMessage());
		}		
	}

}

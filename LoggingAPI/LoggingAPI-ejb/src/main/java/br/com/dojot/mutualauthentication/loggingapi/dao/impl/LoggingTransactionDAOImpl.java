package br.com.dojot.mutualauthentication.loggingapi.dao.impl;

import java.util.ArrayList;
import java.util.Date;
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

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.LoggingTransactionVO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.LoggingTransactionDAO;
import br.com.dojot.mutualauthentication.loggingapi.service.api.ConfigService;
import br.com.dojot.mutualauthentication.loggingapi.utils.LoggingConstants;

@Singleton
public class LoggingTransactionDAOImpl implements LoggingTransactionDAO { 

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
		queries.put("save", session
					.prepare("INSERT INTO loggingtransaction (id, details, level, transaction, node, date) "
							+ "VALUES (:id, :details, :level, :transaction, :node, :date)"));
		queries.put("searcholderthan", session.prepare("SELECT * FROM loggingtransaction WHERE transaction < :transaction ALLOW FILTERING"));
		queries.put("searchinterval", session.prepare("SELECT * FROM loggingtransaction WHERE date >= :init AND date <= :end ALLOW FILTERING"));
	}
		
	@PreDestroy
	public void close() {
		session.close();
		cluster.close();
	}
	
	@Override
	public void add(LoggingTransactionVO vo) {
		try {
			session.execute(queries.get("save").bind()
					.setLong("id", vo.getId())
					.setString("details", vo.getDetails())
					.setString("level", vo.getLevel())
					.setString("transaction", vo.getTransaction())
					.setString("node", vo.getNode())
					.setTimestamp("date", vo.getDate()));
		} catch (Exception ex) {
			System.out.println("Erro ao persisir um loggingtransaction: " + ex.getMessage());
		}		
	}
		
	@Override
	public List<LoggingTransactionVO> searchLoggingTransaction(String transaction) {
		List<LoggingTransactionVO> logs = new ArrayList<LoggingTransactionVO>();
		ResultSet results = session.execute(queries.get("searcholderthan").bind().setString("transaction", transaction));
		for (Row row : results) {
			LoggingTransactionVO vo = new LoggingTransactionVO();
			vo.setId(row.getLong("id"));
			vo.setDetails(row.getString("details"));
			vo.setLevel(row.getString("level"));
			vo.setTransaction(row.getString("transaction"));
			vo.setNode(row.getString("node"));
			vo.setDate(row.getTimestamp("date"));
			logs.add(vo);
		}
		return logs;
	}	
	
	@Override
	public List<LoggingTransactionVO> searchLoggingTransactionPeriod(Date init, Date end) {
		List<LoggingTransactionVO> logs = new ArrayList<LoggingTransactionVO>();
		ResultSet results = session.execute(queries.get("searchinterval").bind().setTimestamp("init", init).setTimestamp("end", end));
		for (Row row : results) {
			LoggingTransactionVO vo = new LoggingTransactionVO();
			vo.setId(row.getLong("id"));
			vo.setDetails(row.getString("details"));
			vo.setLevel(row.getString("level"));
			vo.setTransaction(row.getString("transaction"));
			vo.setNode(row.getString("node"));
			vo.setDate(row.getTimestamp("date"));
			logs.add(vo);
		}
		return logs;
	}

}
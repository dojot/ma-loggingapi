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

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.LoggingVO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.LoggingDAO;
import br.com.dojot.mutualauthentication.loggingapi.service.api.ConfigService;
import br.com.dojot.mutualauthentication.loggingapi.utils.LoggingConstants;

@Singleton
public class LoggingDAOImpl implements LoggingDAO { 

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
		queries.put("save", session.prepare("INSERT INTO logging (id, component, details, level, username, node, date) VALUES (:id, :component, :details, :level, :username, :node, :date)"));
		queries.put("searcholderthan", session.prepare("SELECT * FROM logging WHERE date < :date ALLOW FILTERING"));
		queries.put("searchinterval", session.prepare("SELECT * FROM logging WHERE date >= :init AND date <= :end ALLOW FILTERING"));
		
	}
		
	@PreDestroy
	public void close() {
		session.close();
		cluster.close();
	}
	
	@Override
	public void save(LoggingVO vo) {
		try {
			session.execute(queries.get("save").bind()
					.setLong("id", vo.getId())
					.setString("component", vo.getComponent())
					.setString("details", vo.getDetails())
					.setString("level", vo.getLevel())
					.setString("username", vo.getUsername())
					.setString("node", vo.getNode())
					.setTimestamp("date", vo.getDate()));
		} catch (Exception ex) {
			System.out.println("Erro ao persisir um logging: " + ex.getMessage());
		}		
	}
	
	@Override
	public List<LoggingVO> searchLoggingToClear(Date date) {
		List<LoggingVO> logs = new ArrayList<LoggingVO>();
		ResultSet results = session.execute(queries.get("searcholderthan").bind().setTimestamp("date", date));
		for (Row row : results) {
			LoggingVO vo = new LoggingVO();
			vo.setId(row.getLong("id"));
			vo.setDetails(row.getString("details"));
			vo.setLevel(row.getString("level"));
			vo.setComponent(row.getString("component"));
			vo.setUsername(row.getString("username"));
			vo.setNode(row.getString("node"));
			vo.setDate(row.getTimestamp("date"));
			logs.add(vo);
		}
		return logs;
	}

	@Override
	public List<LoggingVO> searchLoggingPeriod(Date init, Date end) {
		List<LoggingVO> logs = new ArrayList<LoggingVO>();
		ResultSet results = session.execute(queries.get("searchinterval").bind().setTimestamp("init", init).setTimestamp("end", end));
		for (Row row : results) {
			LoggingVO vo = new LoggingVO();
			vo.setId(row.getLong("id"));
			vo.setDetails(row.getString("details"));
			vo.setLevel(row.getString("level"));
			vo.setComponent(row.getString("component"));
			vo.setUsername(row.getString("username"));
			vo.setNode(row.getString("node"));
			vo.setDate(row.getTimestamp("date"));
			logs.add(vo);
		}
		return logs;
	}

}

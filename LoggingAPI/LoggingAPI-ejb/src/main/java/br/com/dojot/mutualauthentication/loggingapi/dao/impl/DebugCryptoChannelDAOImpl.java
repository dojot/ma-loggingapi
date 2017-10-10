package br.com.dojot.mutualauthentication.loggingapi.dao.impl;

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

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.DebugCryptoChannelVO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.DebugCryptoChannelDAO;
import br.com.dojot.mutualauthentication.loggingapi.service.api.ConfigService;
import br.com.dojot.mutualauthentication.loggingapi.utils.LoggingConstants;

@Singleton
public class DebugCryptoChannelDAOImpl implements DebugCryptoChannelDAO {
	
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
					.prepare("INSERT INTO debugcryptochannel (transactionId, ivComponentToServer, ivServerToComponent, keyComponentToServer, keyServerToComponent, provider, sessionId, tagLen, date) "
							+ "VALUES (:transactionId, :ivComponentToServer, :ivServerToComponent, :keyComponentToServer, :keyServerToComponent, :provider, :sessionId, :tagLen, :date)"));
		queries.put("remove", session.prepare("DELETE FROM debugcryptochannel WHERE transactionId = :transactionId"));
	}
		
	@PreDestroy
	public void close() {
		session.close();
		cluster.close();
	}
	
	@Override
	public void save(DebugCryptoChannelVO vo) {
		try {
			session.execute(queries.get("save").bind()
					.setString("ivComponentToServer", vo.getIvComponentToServer())
					.setString("ivServerToComponent", vo.getIvServerToComponent())
					.setString("keyComponentToServer", vo.getKeyComponentToServer())
					.setString("keyServerToComponent", vo.getKeyServerToComponent())
					.setString("provider", vo.getProvider())
					.setString("sessionId", vo.getSessionId())
					.setString("transactionId", vo.getTransactionId())
					.setInt("tagLen", vo.getTagLen())
					.setTimestamp("date", vo.getDate()));
		} catch (Exception ex) {
			System.out.println("Erro ao persisir um debugcryptochannel: " + ex.getMessage());
		}		
	}

	@Override
	public void remove(String transactionId) {
		try {
			session.execute(queries.get("remove").bind().setString("transactionId", transactionId));
		} catch (Exception ex) {
			System.out.println("Erro ao excluir um debugcryptochannel: " + ex.getMessage());
		}
	}
	
}

package br.com.dojot.mutualauthentication.loggingapi.service.impl;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;

import br.com.dojot.mutualauthentication.communication.constants.CommunicationKeysConstants;
import br.com.dojot.mutualauthentication.communication.facade.api.CommunicationFacade;
import br.com.dojot.mutualauthentication.communication.facade.impl.CommunicationFacadeBean;
import br.com.dojot.mutualauthentication.loggingapi.service.api.ConfigService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.UniqueIdService;
import br.com.dojot.mutualauthentication.loggingapi.utils.LoggingConstants;

@Startup
@Singleton
public class UniqueIdServiceImpl implements UniqueIdService {
	
	private RedissonClient client = null;
	
	private String version;
	
	@EJB
	private ConfigService configService;
	
	@PostConstruct
	public void init() {
		Config config = new Config();
    	SentinelServersConfig sentinelConfig = config.useSentinelServers();
    	sentinelConfig.setMasterName(LoggingConstants.REDIS_CLUSTER_MASTER_NAME);
    	CommunicationFacade communicationFacade = new CommunicationFacadeBean();
    	for (String sentinel : communicationFacade.requestRedisSentinelsHosts()) {
            sentinelConfig.addSentinelAddress(sentinel);
    	}
    	version = (String) communicationFacade.requestNodeConfigs().get(CommunicationKeysConstants.KEY_VERSION);
    	client = Redisson.create(config);
	}

	@Override
	public long getNextIdForTable(String table) {
		RAtomicLong currentId = client.getAtomicLong(table + "." + version);
		String limit = configService.findConfigurationByKey(table + "_limit");
		long nextId = currentId.incrementAndGet();
		if(nextId >=  Integer.valueOf(limit)) {
			currentId.set(0);
		}
		return nextId;			
	}

}

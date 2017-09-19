package br.com.cpqd.mutualauthentication.loggingapi.messaging.startup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import br.com.cpqd.mutualauthentication.loggingapi.dao.api.ConfigDAO;
import br.com.cpqd.mutualauthentication.loggingapi.messaging.api.ConsumerStartup;
import br.com.cpqd.mutualauthentication.loggingapi.messaging.consumer.ConsumerLoopDebugComponent;
import br.com.cpqd.mutualauthentication.loggingapi.messaging.consumer.ConsumerLoopDebugCryptoChannel;
import br.com.cpqd.mutualauthentication.loggingapi.messaging.consumer.ConsumerLoopDebugPageProtection;
import br.com.cpqd.mutualauthentication.loggingapi.messaging.consumer.ConsumerLoopDebugReleaseTransaction;
import br.com.cpqd.mutualauthentication.loggingapi.messaging.consumer.ConsumerLoopDebugTransaction;
import br.com.cpqd.mutualauthentication.loggingapi.messaging.consumer.ConsumerLoopGeneric;
import br.com.cpqd.mutualauthentication.loggingapi.messaging.consumer.ConsumerLoopLoggingProcessing;
import br.com.cpqd.mutualauthentication.loggingapi.messaging.consumer.ConsumerLoopLoggingTransaction;
import br.com.cpqd.mutualauthentication.loggingapi.utils.LoggingConstants;
import br.com.cpqd.mutualauthentication.communication.constants.CommunicationKeysConstants;
import br.com.cpqd.mutualauthentication.communication.facade.api.CommunicationFacade;
import br.com.cpqd.mutualauthentication.communication.facade.impl.CommunicationFacadeBean;

@Startup
@Singleton
public class ConsumerStartupImpl implements ConsumerStartup {
	
    @Resource
    ManagedExecutorService managedExecutorService;
    
    @EJB
    private ConfigDAO configDAO;
    
    @Inject
    Instance<ConsumerLoopDebugComponent> consumerLoopDebugComponent;
    
    @Inject
    Instance<ConsumerLoopDebugCryptoChannel> consumerLoopDebugCryptoChannel;
    
    @Inject
    Instance<ConsumerLoopDebugPageProtection> consumerLoopDebugPageProtection;
    
    @Inject
    Instance<ConsumerLoopDebugReleaseTransaction> consumerLoopDebugReleaseTransaction;
    
    @Inject
    Instance<ConsumerLoopDebugTransaction> consumerLoopDebugTransaction;
    
    @Inject
    Instance<ConsumerLoopLoggingProcessing> consumerLoopLoggingProcessing;
    
    @Inject
    Instance<ConsumerLoopLoggingTransaction> consumerLoopLoggingTransaction;
    
    private List<ConsumerLoopGeneric> tasks;

    public void executeAsync() throws ExecutionException, InterruptedException {
    	tasks = new ArrayList<>();
    	CommunicationFacade facade = new CommunicationFacadeBean();
    	String node = (String) facade.requestNodeConfigs().get(CommunicationKeysConstants.KEY_NODE);
    	String version = (String) facade.requestNodeConfigs().get(CommunicationKeysConstants.KEY_VERSION);
    	String bootstrapServers = configDAO.findConfigurationByKey(LoggingConstants.PARAM_KAFKA_BOOTSTRAP_SERVERS);
    	int numberOfConsumers = Integer.valueOf(configDAO.findConfigurationByKey(LoggingConstants.PARAM_KAFKA_NUMBER_OF_CONSUMERS));
	    for (int i=0; i<numberOfConsumers; i++) {
	    	ConsumerLoopDebugComponent cldc = consumerLoopDebugComponent.get();
	    	cldc.setUniqueIdentifier(node, i);
	    	cldc.setVersion(version);
	    	cldc.setBootstrapServers(bootstrapServers);
	    	tasks.add(cldc);
	        this.managedExecutorService.submit(cldc);
	        
	        ConsumerLoopDebugCryptoChannel cldcc = consumerLoopDebugCryptoChannel.get();
	        cldcc.setUniqueIdentifier(node, i);
	        cldcc.setVersion(version);
	        cldcc.setBootstrapServers(bootstrapServers);
	        tasks.add(cldcc);
	        this.managedExecutorService.submit(cldcc);
	        
	        ConsumerLoopDebugPageProtection cldpp = consumerLoopDebugPageProtection.get();
	        cldpp.setUniqueIdentifier(node, i);
	        cldpp.setVersion(version);
	        cldpp.setBootstrapServers(bootstrapServers);
	        tasks.add(cldpp);
	        this.managedExecutorService.submit(cldpp);
	        
	        ConsumerLoopDebugReleaseTransaction cldrt = consumerLoopDebugReleaseTransaction.get();
	        cldrt.setUniqueIdentifier(node, i);
	        cldrt.setVersion(version);
	        cldrt.setBootstrapServers(bootstrapServers);
	        tasks.add(cldrt);
	        this.managedExecutorService.submit(cldrt);
	        
	        ConsumerLoopDebugTransaction cldt = consumerLoopDebugTransaction.get();
	        cldt.setUniqueIdentifier(node, i);
	        cldt.setVersion(version);
	        cldt.setBootstrapServers(bootstrapServers);
	        tasks.add(cldt);
	        this.managedExecutorService.submit(cldt);
	        
	        ConsumerLoopLoggingProcessing cllp = consumerLoopLoggingProcessing.get();
	        cllp.setUniqueIdentifier(node, i);
	        cllp.setVersion(version);
	        cllp.setBootstrapServers(bootstrapServers);
	        tasks.add(cllp);
	        this.managedExecutorService.submit(cllp);
	        
	        ConsumerLoopLoggingTransaction cllt = consumerLoopLoggingTransaction.get();
	        cllt.setUniqueIdentifier(node, i);
	        cllt.setVersion(version);
	        cllt.setBootstrapServers(bootstrapServers);
	        tasks.add(cllt);
	        this.managedExecutorService.submit(cllt);
	    }
    }

	@PostConstruct
	public void init() {
		try {
			this.executeAsync();
			System.out.println("Started all Logging consumers..");
			
		} catch (ExecutionException e) {
			System.out.println("ExecutionException" + e.getMessage());

		} catch (InterruptedException e) {
			System.out.println("InterruptedException" + e.getMessage());
		}
	}

    @PreDestroy
	public void destroy() {
		System.out.println("Removing all Logging consumers");
		for (ConsumerLoopGeneric task : tasks) {
			task.shutdown();
		}
		System.out.println("All Logging consumers were terminated.");
	}

}
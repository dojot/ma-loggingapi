package br.com.cpqd.mutualauthentication.loggingapi.messaging.producer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.cpqd.mutualauthentication.loggingapi.beans.dto.LoggingDTO;
import br.com.cpqd.mutualauthentication.loggingapi.dao.api.ConfigDAO;
import br.com.cpqd.mutualauthentication.loggingapi.messaging.api.LoggingProcessingProducerService;
import br.com.cpqd.mutualauthentication.loggingapi.utils.LoggingConstants;
import br.com.cpqd.mutualauthentication.communication.constants.CommunicationKeysConstants;
import br.com.cpqd.mutualauthentication.communication.facade.api.CommunicationFacade;
import br.com.cpqd.mutualauthentication.communication.facade.impl.CommunicationFacadeBean;

@Startup
@Singleton
public class LoggingProcessingProducerServiceImpl extends Thread implements LoggingProcessingProducerService {
	private ProducerServiceImpl producer;
	
	@EJB
	private ConfigDAO configDAO;
    
	@PostConstruct
	public void init() {
		CommunicationFacade facade = new CommunicationFacadeBean();
		producer = new ProducerServiceImpl(facade.requestKafkaBrokers(),
				LoggingConstants.TOPIC_LOGGING_PROCESSING,
				(String) facade.requestNodeConfigs().get(CommunicationKeysConstants.KEY_VERSION), "log.logprocessing.");
	}
	
	@PreDestroy
	public void close() {
		producer.close();
	}

	@Override
	public void produce(LoggingDTO dto) {
		producer.produce(dto);
	}

}
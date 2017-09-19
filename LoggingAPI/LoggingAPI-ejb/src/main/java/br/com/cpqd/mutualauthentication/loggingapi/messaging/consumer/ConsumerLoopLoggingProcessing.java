package br.com.cpqd.mutualauthentication.loggingapi.messaging.consumer;

import javax.ejb.EJB;

import org.apache.commons.lang3.exception.ExceptionUtils;

import br.com.cpqd.mutualauthentication.loggingapi.service.api.LoggingService;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.LoggingService.Level;
import br.com.cpqd.mutualauthentication.loggingapi.utils.LoggingConstants;

public class ConsumerLoopLoggingProcessing extends ConsumerLoopGeneric {
	@EJB
	private LoggingService loggingService;

	public ConsumerLoopLoggingProcessing() {
		super(LoggingConstants.TOPIC_LOGGING_PROCESSING, "logginggroup", "loggingprocessing");
	}

	@Override
	public void process(String json) {
		try {
			loggingService.log(json);
		} catch (Exception ex) {
			loggingService.saveLogging(Level.ERROR, "LOGGING", "SISTEMA", ex.getMessage() + ".\n" + ExceptionUtils.getStackTrace(ex));
		}
	}
	
}
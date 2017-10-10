package br.com.dojot.mutualauthentication.loggingapi.messaging.consumer;

import javax.ejb.EJB;

import org.apache.commons.lang3.exception.ExceptionUtils;

import br.com.dojot.mutualauthentication.loggingapi.service.api.DebugTransactionService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.LoggingService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.LoggingService.Level;
import br.com.dojot.mutualauthentication.loggingapi.utils.LoggingConstants;

public class ConsumerLoopDebugTransaction extends ConsumerLoopGeneric {	
	@EJB
	private DebugTransactionService debugTransactionService;
	
	@EJB
	private LoggingService loggingService;

	public ConsumerLoopDebugTransaction() {
		super(LoggingConstants.TOPIC_DEBUG_TRANSACTION, "logginggroup", "debugtransaction");
	}
	
	@Override
	public void process(String json) {
		try {
			debugTransactionService.save(json);
		} catch (Exception ex) {
			loggingService.saveLogging(Level.ERROR, "LOGGING", "SISTEMA", ex.getMessage() + ".\n" + ExceptionUtils.getStackTrace(ex));
		}
	}
	
}
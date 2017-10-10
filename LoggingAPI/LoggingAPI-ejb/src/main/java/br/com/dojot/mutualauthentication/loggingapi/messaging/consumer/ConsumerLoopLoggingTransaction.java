package br.com.dojot.mutualauthentication.loggingapi.messaging.consumer;

import javax.ejb.EJB;

import org.apache.commons.lang3.exception.ExceptionUtils;

import br.com.dojot.mutualauthentication.loggingapi.service.api.LoggingService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.LoggingTransactionService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.LoggingService.Level;
import br.com.dojot.mutualauthentication.loggingapi.utils.LoggingConstants;

public class ConsumerLoopLoggingTransaction extends ConsumerLoopGeneric {
	
	@EJB
	private LoggingTransactionService loggingTransactionService;
	
	@EJB
	private LoggingService loggingService;


	public ConsumerLoopLoggingTransaction() {
		super(LoggingConstants.TOPIC_LOGGING_TRANSACTION, "logginggroup", "loggingtransaction");
	}

	@Override
	public void process(String json) {
		try {
			loggingTransactionService.log(json);
		} catch (Exception ex) {
			loggingService.saveLogging(Level.ERROR, "LOGGING", "SISTEMA", ex.getMessage() + ".\n" + ExceptionUtils.getStackTrace(ex));
		}
	}
	
}
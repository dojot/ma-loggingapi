package br.com.cpqd.mutualauthentication.loggingapi.messaging.consumer;

import javax.ejb.EJB;

import org.apache.commons.lang3.exception.ExceptionUtils;

import br.com.cpqd.mutualauthentication.loggingapi.service.api.DebugTransactionService;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.LoggingService;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.LoggingService.Level;
import br.com.cpqd.mutualauthentication.loggingapi.utils.LoggingConstants;

public class ConsumerLoopDebugReleaseTransaction extends ConsumerLoopGeneric {
	
	@EJB
	private DebugTransactionService debugTransactionService;
	
	@EJB
	private LoggingService loggingService;

	public ConsumerLoopDebugReleaseTransaction() {
		super(LoggingConstants.TOPIC_DEBUG_RELEASE_TRANSACTION, "logginggroup", "debugreleasetransaction");
	}

	@Override
	public void process(String json) {
		try {
			debugTransactionService.release(json);
		} catch (Exception ex) {
			loggingService.saveLogging(Level.ERROR, "LOGGING", "SISTEMA", ex.getMessage() + ".\n" + ExceptionUtils.getStackTrace(ex));
		}
	}
	
}
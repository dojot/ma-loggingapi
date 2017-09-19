package br.com.cpqd.mutualauthentication.loggingapi.messaging.consumer;

import javax.ejb.EJB;

import org.apache.commons.lang3.exception.ExceptionUtils;

import br.com.cpqd.mutualauthentication.loggingapi.service.api.DebugPageProtectionService;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.LoggingService;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.LoggingService.Level;
import br.com.cpqd.mutualauthentication.loggingapi.utils.LoggingConstants;

public class ConsumerLoopDebugPageProtection extends ConsumerLoopGeneric {

	
	@EJB
	private DebugPageProtectionService debugPageProtectionService;
	
	@EJB
	private LoggingService loggingService;

	public ConsumerLoopDebugPageProtection() {
		super(LoggingConstants.TOPIC_DEBUG_PAGE_PROTECTION, "logginggroup", "debugpageprotection");
	}

	@Override
	public void process(String json) {
		try {
			debugPageProtectionService.save(json);
		} catch (Exception ex) {
			loggingService.saveLogging(Level.ERROR, "LOGGING", "SISTEMA", ex.getMessage() + ".\n" + ExceptionUtils.getStackTrace(ex));
		}
	}
	
}
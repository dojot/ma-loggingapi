package br.com.dojot.mutualauthentication.loggingapi.messaging.consumer;

import javax.ejb.EJB;

import org.apache.commons.lang3.exception.ExceptionUtils;

import br.com.dojot.mutualauthentication.loggingapi.service.api.DebugComponentService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.LoggingService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.LoggingService.Level;
import br.com.dojot.mutualauthentication.loggingapi.utils.LoggingConstants;

public class ConsumerLoopDebugComponent extends ConsumerLoopGeneric {	
	@EJB
	private DebugComponentService debugComponentService;
	
	@EJB
	private LoggingService loggingService;

	public ConsumerLoopDebugComponent() {
		super(LoggingConstants.TOPIC_DEBUG_COMPONENT, "logginggroup", "debugcomponent");
	}

	@Override
	public void process(String json) {
		try {
			debugComponentService.save(json);
		} catch (Exception ex) {
			loggingService.saveLogging(Level.ERROR, "LOGGING", "SISTEMA", ex.getMessage() + ".\n" + ExceptionUtils.getStackTrace(ex));
		}
	}
	
}
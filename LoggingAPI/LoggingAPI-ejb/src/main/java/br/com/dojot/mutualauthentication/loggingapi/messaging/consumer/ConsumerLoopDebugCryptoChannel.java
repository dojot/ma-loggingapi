package br.com.dojot.mutualauthentication.loggingapi.messaging.consumer;

import javax.ejb.EJB;

import org.apache.commons.lang3.exception.ExceptionUtils;

import br.com.dojot.mutualauthentication.loggingapi.service.api.DebugCryptoChannelService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.LoggingService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.LoggingService.Level;
import br.com.dojot.mutualauthentication.loggingapi.utils.LoggingConstants;

public class ConsumerLoopDebugCryptoChannel extends ConsumerLoopGeneric {
	
	@EJB
	private DebugCryptoChannelService debugCryptoChannelService;
	
	@EJB
	private LoggingService loggingService;

	public ConsumerLoopDebugCryptoChannel() {
		super(LoggingConstants.TOPIC_DEBUG_CRYPTO_CHANNEL, "logginggroup", "debugcryptochannel");
	}

	@Override
	public void process(String json) {
		try {
			debugCryptoChannelService.save(json);
		} catch (Exception ex) {
			loggingService.saveLogging(Level.ERROR, "LOGGING", "SISTEMA", ex.getMessage() + ".\n" + ExceptionUtils.getStackTrace(ex));
		}
	}
	
}
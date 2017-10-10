package br.com.dojot.mutualauthentication.loggingapi.messaging.api;

import br.com.dojot.mutualauthentication.loggingapi.beans.dto.LoggingDTO;

public interface LoggingProcessingProducerService {
	
	void produce(LoggingDTO dto);
}

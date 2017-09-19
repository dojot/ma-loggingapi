package br.com.cpqd.mutualauthentication.loggingapi.messaging.api;

import br.com.cpqd.mutualauthentication.loggingapi.beans.dto.LoggingDTO;

public interface LoggingProcessingProducerService {
	
	void produce(LoggingDTO dto);
}

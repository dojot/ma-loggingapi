package br.com.dojot.mutualauthentication.loggingapi.service.api;

import javax.ejb.Local;

@Local
public interface DebugPageProtectionService {

	void save(String json);
	
	void release(String transactionId);
}

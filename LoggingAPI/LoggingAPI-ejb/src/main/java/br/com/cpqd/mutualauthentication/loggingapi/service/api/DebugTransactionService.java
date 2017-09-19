package br.com.cpqd.mutualauthentication.loggingapi.service.api;

import javax.ejb.Local;

@Local
public interface DebugTransactionService {

	void save(String json);
	
	void release(String json);
}

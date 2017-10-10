package br.com.dojot.mutualauthentication.loggingapi.service.api;

import javax.ejb.Local;

@Local
public interface DebugCryptoChannelService {

	void save(String json);
	
	void release(String transactionId);
}

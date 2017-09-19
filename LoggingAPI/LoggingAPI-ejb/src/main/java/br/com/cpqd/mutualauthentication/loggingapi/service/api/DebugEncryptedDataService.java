package br.com.cpqd.mutualauthentication.loggingapi.service.api;

import java.util.List;

import javax.ejb.Local;

@Local
public interface DebugEncryptedDataService {

	void save(List<String> encryptedData, String transaction);
	
	void release(String transactionId);
}

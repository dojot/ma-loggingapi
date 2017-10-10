package br.com.dojot.mutualauthentication.loggingapi.service.api;

import javax.ejb.Local;

import org.json.JSONObject;

@Local
public interface DebugVariableMapService {

	void save(JSONObject json, String transaction);
	
	void release(String transactionId);
}

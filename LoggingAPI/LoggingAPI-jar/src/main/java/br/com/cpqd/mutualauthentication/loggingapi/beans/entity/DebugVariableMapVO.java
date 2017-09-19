package br.com.cpqd.mutualauthentication.loggingapi.beans.entity;

import java.io.Serializable;

public class DebugVariableMapVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8065240327002483832L;
	
	private String key;
	
	private String data;
	
	private String transaction;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

}
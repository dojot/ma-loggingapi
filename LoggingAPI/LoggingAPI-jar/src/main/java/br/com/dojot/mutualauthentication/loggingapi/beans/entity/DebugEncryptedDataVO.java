package br.com.dojot.mutualauthentication.loggingapi.beans.entity;

import java.io.Serializable;

public class DebugEncryptedDataVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7523315062910711416L;

	private String encryptedData;
	
	private String transaction;

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

}
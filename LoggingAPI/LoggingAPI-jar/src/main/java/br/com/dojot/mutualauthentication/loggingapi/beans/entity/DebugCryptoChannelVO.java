package br.com.dojot.mutualauthentication.loggingapi.beans.entity;

import java.io.Serializable;
import java.util.Date;

public class DebugCryptoChannelVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2260705852168529056L;
	
	private Date date;

	private String sessionId;

	private String transactionId;

	private String provider;

	private String keyServerToComponent;

	private String keyComponentToServer;

	private String ivServerToComponent;

	private String ivComponentToServer;

	private Integer tagLen;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getKeyServerToComponent() {
		return keyServerToComponent;
	}

	public void setKeyServerToComponent(String keyServerToComponent) {
		this.keyServerToComponent = keyServerToComponent;
	}

	public String getKeyComponentToServer() {
		return keyComponentToServer;
	}

	public void setKeyComponentToServer(String keyComponentToServer) {
		this.keyComponentToServer = keyComponentToServer;
	}

	public String getIvServerToComponent() {
		return ivServerToComponent;
	}

	public void setIvServerToComponent(String ivServerToComponent) {
		this.ivServerToComponent = ivServerToComponent;
	}

	public String getIvComponentToServer() {
		return ivComponentToServer;
	}

	public void setIvComponentToServer(String ivComponentToServer) {
		this.ivComponentToServer = ivComponentToServer;
	}

	public Integer getTagLen() {
		return tagLen;
	}

	public void setTagLen(Integer tagLen) {
		this.tagLen = tagLen;
	}

}
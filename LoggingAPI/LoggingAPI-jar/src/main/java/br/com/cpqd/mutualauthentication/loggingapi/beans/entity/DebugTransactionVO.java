package br.com.cpqd.mutualauthentication.loggingapi.beans.entity;

import java.io.Serializable;
import java.util.Date;

public class DebugTransactionVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6549463905997563084L;
	
	private Date date;

	private String client;
	
	private String transaction;

	private String session;

	private String component;

	private String dateClient;

	private String type;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getDateClient() {
		return dateClient;
	}

	public void setDateClient(String dateClient) {
		this.dateClient = dateClient;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
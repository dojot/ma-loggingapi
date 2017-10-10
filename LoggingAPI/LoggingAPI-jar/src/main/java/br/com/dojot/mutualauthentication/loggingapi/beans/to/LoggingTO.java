package br.com.dojot.mutualauthentication.loggingapi.beans.to;

import java.io.Serializable;

public class LoggingTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1413829835384655898L;

	private String date;

    private String level;

    private String component;

    private String transaction;

    private String details;

    private String node;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

}

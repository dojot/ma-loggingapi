package br.com.cpqd.mutualauthentication.loggingapi.beans.to;

import java.io.Serializable;

public class FiltersTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3598674585349163038L;

	private String type;
	
	private String init;
	
	private String end;
	
	private String transaction;
	
	private String session;

	private String component;

	private String node;

	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

}

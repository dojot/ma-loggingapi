package br.com.dojot.mutualauthentication.loggingapi.beans.to;

import java.io.Serializable;

public class LoggingGeneratorTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2600279091700350263L;
	
	private String component;
	
	private String level;

	private String username;
	
	private String details;
	
	private String node;

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

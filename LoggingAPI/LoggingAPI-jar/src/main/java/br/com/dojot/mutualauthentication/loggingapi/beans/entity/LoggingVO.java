package br.com.dojot.mutualauthentication.loggingapi.beans.entity;

import java.io.Serializable;
import java.util.Date;

public class LoggingVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2895173232081782461L;

	private Long id;

	private String component;

	private String username;

	private String details;

	private Date date;

	private String level;
	
	private String node;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

}
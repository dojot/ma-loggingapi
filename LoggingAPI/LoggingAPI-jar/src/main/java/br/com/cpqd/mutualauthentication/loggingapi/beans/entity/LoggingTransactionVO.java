package br.com.cpqd.mutualauthentication.loggingapi.beans.entity;

import java.io.Serializable;
import java.util.Date;

public class LoggingTransactionVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8493236674506905160L;

	private Long id;
	
	private String details;
	
	private Date date;

	private String level;

	private String transaction;
	
	private String node;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

}
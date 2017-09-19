package br.com.cpqd.mutualauthentication.loggingapi.beans.entity;

import java.io.Serializable;
import java.util.Date;

public class DebugPageProtectionVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3749055946961122623L;
	
	private Date date;

	private String transactionId;

	private String data;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
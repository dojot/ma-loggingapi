package br.com.cpqd.mutualauthentication.loggingapi.beans.entity;

import java.io.Serializable;
import java.util.Date;

public class DebugComponentVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2906832310677985324L;
	
	private Date date;

	private String transactionId;

	private String preobfuscationParam;
	
	private String fieldSizes;

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

	public String getPreobfuscationParam() {
		return preobfuscationParam;
	}

	public void setPreobfuscationParam(String preobfuscationParam) {
		this.preobfuscationParam = preobfuscationParam;
	}

	public String getFieldSizes() {
		return fieldSizes;
	}

	public void setFieldSizes(String fieldSizes) {
		this.fieldSizes = fieldSizes;
	}

}
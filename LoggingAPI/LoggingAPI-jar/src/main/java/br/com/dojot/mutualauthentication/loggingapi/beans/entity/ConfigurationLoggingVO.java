package br.com.dojot.mutualauthentication.loggingapi.beans.entity;

import java.io.Serializable;

public class ConfigurationLoggingVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -568400507553329328L;

	private String id;

    private String configuration;

    private String result;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}

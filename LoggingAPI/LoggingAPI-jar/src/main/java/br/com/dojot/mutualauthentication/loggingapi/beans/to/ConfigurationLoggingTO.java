package br.com.dojot.mutualauthentication.loggingapi.beans.to;

import java.io.Serializable;

public class ConfigurationLoggingTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3323894108134139346L;

    private String configuration;

    private String result;

    private String description;

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

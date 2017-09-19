package br.com.cpqd.mutualauthentication.loggingapi.dao.api;

import java.util.List;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.ConfigurationLoggingVO;

public interface ConfigurationLoggingDAO {

    List<ConfigurationLoggingVO> search();
    
    ConfigurationLoggingVO findByConfiguration(String configuration);
    
    void update(String id, String result);
}

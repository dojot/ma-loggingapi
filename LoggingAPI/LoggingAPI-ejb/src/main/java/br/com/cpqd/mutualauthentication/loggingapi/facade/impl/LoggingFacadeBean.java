package br.com.cpqd.mutualauthentication.loggingapi.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.cpqd.mutualauthentication.loggingapi.beans.to.ConfigurationLoggingTO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.FiltersTO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.LoggingGeneratorTO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.LoggingTO;
import br.com.cpqd.mutualauthentication.loggingapi.facade.api.LoggingFacade;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.ConfigService;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.ConfigurationLoggingService;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.IndexedLogService;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.LoggingService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class LoggingFacadeBean implements LoggingFacade {

    @EJB
    private ConfigService configService;

    @EJB
    private ConfigurationLoggingService configurationLoggingService;

    @EJB
    private LoggingService loggingService;

    @EJB
    private IndexedLogService indexedLogService;

    @Override
    public List<ConfigurationLoggingTO> searchConfigurationLoggingTO() {
        return configurationLoggingService.search();
    }

    @Override
    public ConfigurationLoggingTO updateConfigurationLoggingTO(ConfigurationLoggingTO to) {
        return configurationLoggingService.update(to);
    }

	@Override
	public List<LoggingTO> searchLoggingFilters(FiltersTO filters) {
		return loggingService.searchLoggingFilters(filters);
	}

	@Override
	public List<LoggingTO> searchIndexedLogFilters(FiltersTO filters) {
		return indexedLogService.searchFilter(filters);
	}

	@Override
	public String findConfigurationByKey(String key) {
		return configService.findConfigurationByKey(key);
	}

	@Override
	public void logGenerator(LoggingGeneratorTO log) {
		loggingService.logGenerator(log);
	}

}

package br.com.cpqd.mutualauthentication.loggingapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.ConfigurationLoggingVO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.ConfigurationLoggingTO;
import br.com.cpqd.mutualauthentication.loggingapi.dao.api.ConfigurationLoggingDAO;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.ConfigurationLoggingService;

@Stateless
public class ConfigurationLoggingServiceImpl implements ConfigurationLoggingService {

    @EJB
    private ConfigurationLoggingDAO configurationLoggingDAO;

    @Override
    public List<ConfigurationLoggingTO> search() {
        List<ConfigurationLoggingTO> out = new ArrayList<ConfigurationLoggingTO>();
        ConfigurationLoggingTO to = null;
        List<ConfigurationLoggingVO> lstVO = configurationLoggingDAO.search();
        for (ConfigurationLoggingVO vo : lstVO) {
            to = new ConfigurationLoggingTO();
            to.setConfiguration(vo.getConfiguration());
            to.setDescription(vo.getDescription());
            to.setResult(vo.getResult());
            out.add(to);
        }
        return out;
    }

    @Override
    public ConfigurationLoggingTO update(ConfigurationLoggingTO to) {
        ConfigurationLoggingVO vo = configurationLoggingDAO.findByConfiguration(to.getConfiguration());
        configurationLoggingDAO.update(vo.getId(), to.getResult());
        return to;
    }

	@Override
	public ConfigurationLoggingTO findByConfiguration(String configuration) {
		ConfigurationLoggingVO vo = configurationLoggingDAO.findByConfiguration(configuration);
		ConfigurationLoggingTO to = new ConfigurationLoggingTO();
		to.setConfiguration(vo.getConfiguration());
		to.setDescription(vo.getDescription());
		to.setResult(vo.getResult());
		return to;
	}
}

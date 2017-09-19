package br.com.cpqd.mutualauthentication.loggingapi.listener;

import java.util.LinkedHashMap;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.cpqd.mutualauthentication.communication.constants.CommunicationKeysConstants;
import br.com.cpqd.mutualauthentication.communication.facade.api.CommunicationFacade;
import br.com.cpqd.mutualauthentication.communication.facade.impl.CommunicationFacadeBean;
import br.com.cpqd.mutualauthentication.loggingapi.facade.api.LoggingFacade;
import br.com.cpqd.mutualauthentication.registry.facade.api.RegistryFacade;
import br.com.cpqd.mutualauthentication.registry.facade.impl.RegistryFacadeBean;

@WebListener
public class ServiceRegistryMicroserviceListener implements ServletContextListener {

	@EJB
	private LoggingFacade loggingFacade;

	private LinkedHashMap<String, Object> params;

	private String targetServiceRegistry;

	private String pathServiceRegistryRegister;

	private String pathServiceRegistryUnregister;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {		
		CommunicationFacade facade = new CommunicationFacadeBean();
		params = facade.requestNodeConfigs();
		params.put("microservice", arg0.getServletContext().getInitParameter("microserviceName"));
		params.put("context", arg0.getServletContext().getContextPath());
		targetServiceRegistry = loggingFacade.findConfigurationByKey(CommunicationKeysConstants.SERVICE_REGISTRY_TARGET);
		pathServiceRegistryRegister = loggingFacade.findConfigurationByKey(CommunicationKeysConstants.SERVICE_REGISTRY_PATH_REGISTER);
		pathServiceRegistryUnregister = loggingFacade.findConfigurationByKey(CommunicationKeysConstants.SERVICE_REGISTRY_PATH_UNREGISTER);
        RegistryFacade registryFacade = new RegistryFacadeBean();
		registryFacade.register(targetServiceRegistry, pathServiceRegistryRegister, params);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
        RegistryFacade registryFacade = new RegistryFacadeBean();
		registryFacade.unregister(targetServiceRegistry, pathServiceRegistryUnregister, params);
	}

}

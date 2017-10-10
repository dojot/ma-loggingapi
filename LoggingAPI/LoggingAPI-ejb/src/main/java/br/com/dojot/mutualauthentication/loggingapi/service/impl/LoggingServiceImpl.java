package br.com.dojot.mutualauthentication.loggingapi.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.json.JSONObject;

import br.com.dojot.mutualauthentication.loggingapi.beans.dto.LoggingDTO;
import br.com.dojot.mutualauthentication.loggingapi.beans.entity.LoggingVO;
import br.com.dojot.mutualauthentication.loggingapi.beans.to.FiltersTO;
import br.com.dojot.mutualauthentication.loggingapi.beans.to.LoggingGeneratorTO;
import br.com.dojot.mutualauthentication.loggingapi.beans.to.LoggingTO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.LoggingDAO;
import br.com.dojot.mutualauthentication.loggingapi.messaging.api.LoggingProcessingProducerService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.ConfigurationLoggingService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.IndexedLogService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.LoggingService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.LoggingTransactionService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.UniqueIdService;
import br.com.dojot.mutualauthentication.loggingapi.utils.DateUtils;
import br.com.dojot.mutualauthentication.communication.facade.api.CommunicationFacade;
import br.com.dojot.mutualauthentication.communication.facade.impl.CommunicationFacadeBean;

@Stateless
public class LoggingServiceImpl implements LoggingService {

	@EJB
	private ConfigurationLoggingService configurationLoggingService;
	
	@EJB
	private LoggingProcessingProducerService loggingProcessingProducerService;

	@EJB
	private LoggingDAO loggingDAO;	

	@EJB
	private IndexedLogService indexedLogService;	

	@EJB
	private LoggingTransactionService loggingTransactionService;
	
	@EJB
	private UniqueIdService uniqueIdService;

	@Inject
	private DateUtils dateUtils;
	
	@Override
	public void logGenerator(LoggingGeneratorTO log) {
		LoggingVO vo = new LoggingVO();
		vo.setId(uniqueIdService.getNextIdForTable("logging"));
		vo.setComponent(log.getComponent());
		vo.setDate(new Date());
		vo.setDetails(log.getDetails());
		vo.setLevel(log.getLevel());
		vo.setUsername(log.getUsername());
		vo.setNode(log.getNode());
		indexedLogService.save(vo);
	}	
	
	@Override
	public void log(String logging) {
		JSONObject jsonObject = new JSONObject(logging);
		String level = jsonObject.getString("level");
		String component = jsonObject.getString("component");;
		String username = jsonObject.getString("username");
		String details = jsonObject.getString("details");
		String node = jsonObject.getString("node");
		LoggingVO loggingVO = new LoggingVO();
		loggingVO.setDetails(details);
		loggingVO.setUsername(username);
		loggingVO.setDate(new Date());
		loggingVO.setLevel(level);
		loggingVO.setComponent(component);
		loggingVO.setId(uniqueIdService.getNextIdForTable("logging"));
		loggingVO.setNode(node);
		indexedLogService.save(loggingVO);
	}	

	@Override
	public List<LoggingTO> searchLoggingFilters(FiltersTO filters) {
		List<LoggingTO> out = new ArrayList<LoggingTO>();
		List<LoggingVO> lst = new ArrayList<LoggingVO>();
		if (filters.getType().equals("T") || ((null != filters.getTransaction()) && (!"".equals(filters.getTransaction())))) {
			out = loggingTransactionService.searchLoggingTransactionFilters(filters);
		} else {
			if ((null != filters.getInit()) && (!"".equals(filters.getInit())) ||
				(null != filters.getEnd()) && (!"".equals(filters.getEnd()))) {
					Calendar init = Calendar.getInstance();
					init.setTime(dateUtils.convertFormatStringInDate(filters.getInit()));
					init.set(Calendar.HOUR_OF_DAY, 0);
					init.set(Calendar.MINUTE, 0);
					init.set(Calendar.SECOND, 0);
					Calendar end = Calendar.getInstance();
					end.setTime(dateUtils.convertFormatStringInDate(filters.getEnd()));
					end.set(Calendar.HOUR_OF_DAY, 23);
					end.set(Calendar.MINUTE, 59);
					end.set(Calendar.SECOND, 59);
					lst = loggingDAO.searchLoggingPeriod(init.getTime(), end.getTime());
			}
			if (null != lst && lst.size() > 0) {
				Collections.sort(lst, new Comparator<LoggingVO>() {
					public int compare(LoggingVO sp1, LoggingVO sp2) {
						return sp2.getDate().compareTo(sp1.getDate());
					}
				});
				for (LoggingVO vo : lst) {
					LoggingTO to = new LoggingTO();
					to.setDate(dateUtils.convertFormatDateInString(vo.getDate()));
					to.setComponent(vo.getComponent());
					to.setDetails(vo.getDetails());
					to.setLevel(vo.getLevel());
					out.add(to);
				}
			}
		}
		return out;
	}

	@Override
	public void saveLogging(Level level, String component, String username, String details) {
		CommunicationFacade facade = new CommunicationFacadeBean();
		LoggingDTO dto = new LoggingDTO();
		dto.setComponent(component);
		dto.setDetails(details);
		dto.setLevel(level.toString());
		dto.setUsername(username);
		dto.setNode((String)facade.requestNodeConfigs().get("node"));
		loggingProcessingProducerService.produce(dto);
	}

}

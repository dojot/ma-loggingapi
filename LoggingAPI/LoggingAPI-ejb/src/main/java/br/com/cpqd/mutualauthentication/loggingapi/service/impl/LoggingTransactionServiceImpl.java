package br.com.cpqd.mutualauthentication.loggingapi.service.impl;

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

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.LoggingTransactionVO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.FiltersTO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.LoggingTO;
import br.com.cpqd.mutualauthentication.loggingapi.dao.api.LoggingDAO;
import br.com.cpqd.mutualauthentication.loggingapi.dao.api.LoggingTransactionDAO;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.ConfigurationLoggingService;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.LoggingTransactionService;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.UniqueIdService;
import br.com.cpqd.mutualauthentication.loggingapi.utils.DateUtils;

@Stateless
public class LoggingTransactionServiceImpl implements LoggingTransactionService {

	@EJB
	private ConfigurationLoggingService configurationLoggingService;

	@EJB
	private LoggingDAO loggingDAO;

	@EJB
	private LoggingTransactionDAO loggingTransactionDAO;
	
	@EJB
	private UniqueIdService uniqueIdService;

	@Inject
	private DateUtils dateUtils;

	@Override
	public void log(String logging) {
		JSONObject jsonObject = new JSONObject(logging);
		String level = jsonObject.getString("level");
		String transaction = jsonObject.getString("transaction");;
		String details = jsonObject.getString("details");
		String node = jsonObject.getString("node");
		LoggingTransactionVO vo = new LoggingTransactionVO();
		vo.setDate(new Date());
		vo.setDetails(details);
		vo.setLevel(level);
		vo.setTransaction(transaction);
		vo.setId(uniqueIdService.getNextIdForTable("loggingtransaction"));
		vo.setNode(node);
		loggingTransactionDAO.add(vo);		
	}

	@Override
	public List<LoggingTO> searchLoggingTransactionFilters(FiltersTO filters) {
		List<LoggingTO> out = new ArrayList<LoggingTO>();
		List<LoggingTransactionVO> lst = new ArrayList<LoggingTransactionVO>();
		if ((null != filters.getInit()) && (!"".equals(filters.getInit())) ||
			(null != filters.getEnd()) && (!"".equals(filters.getEnd())) ||
			(null != filters.getTransaction()) && (!"".equals(filters.getTransaction()))) {
			if ((null != filters.getTransaction()) && (!"".equals(filters.getTransaction()))) {
				lst = loggingTransactionDAO.searchLoggingTransaction(filters.getTransaction());
			}
			if ((null != filters.getInit()) && (!"".equals(filters.getInit())) &&
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
				lst = loggingTransactionDAO.searchLoggingTransactionPeriod(init.getTime(), end.getTime());
			}
		}
		if (null != lst && lst.size() > 0) {
			Collections.sort(lst, new Comparator<LoggingTransactionVO>() {
				public int compare(LoggingTransactionVO sp1, LoggingTransactionVO sp2) {
					return sp2.getDate().compareTo(sp1.getDate());
				}
			});
			for (LoggingTransactionVO vo : lst) {
				LoggingTO to = new LoggingTO();
				to.setDate(dateUtils.convertFormatDateInString(vo.getDate()));
				to.setTransaction(vo.getTransaction());
				to.setDetails(vo.getDetails());
				to.setLevel(vo.getLevel());
				out.add(to);
			}
		}
		return out;
	}

}

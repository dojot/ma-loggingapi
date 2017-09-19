package br.com.cpqd.mutualauthentication.loggingapi.service.impl;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.json.JSONObject;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.DebugPageProtectionVO;
import br.com.cpqd.mutualauthentication.loggingapi.dao.api.DebugPageProtectionDAO;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.DebugPageProtectionService;

@Stateless
public class DebugPageProtectionServiceImpl implements DebugPageProtectionService {

	@EJB
	private DebugPageProtectionDAO debugPageProtectionDAO;

	@Override
	public void save(String json) {
		JSONObject jsonObject = new JSONObject(json);
		String data = jsonObject.getString("data");
		String transactionId = jsonObject.getString("transactionId");
		DebugPageProtectionVO vo = new DebugPageProtectionVO();
		vo.setDate(new Date());
		vo.setData(data);
		vo.setTransactionId(transactionId);
		debugPageProtectionDAO.save(vo);
	}

	@Override
	public void release(String transactionId) {
		debugPageProtectionDAO.remove(transactionId);
	}

}

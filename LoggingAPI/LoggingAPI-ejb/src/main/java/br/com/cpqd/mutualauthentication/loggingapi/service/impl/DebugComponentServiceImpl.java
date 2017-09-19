package br.com.cpqd.mutualauthentication.loggingapi.service.impl;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.json.JSONObject;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.DebugComponentVO;
import br.com.cpqd.mutualauthentication.loggingapi.dao.api.DebugComponentDAO;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.DebugComponentService;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.DebugVariableMapService;

@Stateless
public class DebugComponentServiceImpl implements DebugComponentService {

	@EJB
	private DebugVariableMapService debugVariableMapService;

	@EJB
	private DebugComponentDAO debugComponentDAO;

	@Override
	public void save(String json) {
		JSONObject jsonObject = new JSONObject(json);
		String fieldSizes = jsonObject.getString("fieldSizes");
		String preobfuscationParam = jsonObject.getString("preobfuscationParam");
		String transactionId = jsonObject.getString("transactionId");
		DebugComponentVO vo = new DebugComponentVO();
		vo.setDate(new Date());
		vo.setFieldSizes(fieldSizes);
		vo.setPreobfuscationParam(preobfuscationParam);
		vo.setTransactionId(transactionId);
		debugComponentDAO.save(vo);
		JSONObject jsonVariableMap = jsonObject.getJSONObject("variableMap");
		debugVariableMapService.save(jsonVariableMap, transactionId);
	}

	@Override
	public void release(String transactionId) {
		debugComponentDAO.remove(transactionId);	
	}

}

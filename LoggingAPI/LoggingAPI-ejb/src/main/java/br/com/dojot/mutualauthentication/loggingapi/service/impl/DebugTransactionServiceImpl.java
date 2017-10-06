package br.com.dojot.mutualauthentication.loggingapi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.DebugTransactionVO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.DebugTransactionDAO;
import br.com.dojot.mutualauthentication.loggingapi.service.api.DebugComponentService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.DebugCryptoChannelService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.DebugEncryptedDataService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.DebugPageProtectionService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.DebugTransactionService;
import br.com.dojot.mutualauthentication.loggingapi.service.api.DebugVariableMapService;

@Stateless
public class DebugTransactionServiceImpl implements DebugTransactionService {

	@EJB
	private DebugEncryptedDataService debugEncryptedDataService;

	@EJB
	private DebugComponentService debugComponentService;

	@EJB
	private DebugCryptoChannelService debugCryptoChannelService;

	@EJB
	private DebugVariableMapService debugVariableMapService;

	@EJB
	private DebugPageProtectionService debugPageProtectionService;

	@EJB
	private DebugTransactionDAO debugTransactionDAO;

	@Override
	public void save(String json) {
		JSONObject jsonObject = new JSONObject(json);
		String client = jsonObject.getString("idClient");
		String transaction = jsonObject.getString("idTransaction");
		String session = jsonObject.getString("idSession");
		String component = jsonObject.getString("idComponent");
		String date = jsonObject.getString("date");
		String type = jsonObject.getString("type");
		DebugTransactionVO vo = new DebugTransactionVO();
		vo.setDate(new Date());
		vo.setClient(client);
		vo.setComponent(component);
		vo.setDateClient(date);
		vo.setSession(session);
		vo.setTransaction(transaction);
		vo.setType(type);
		debugTransactionDAO.save(vo);		
		List<String> encryptedData = convertJSONObjectInListString(jsonObject, "encryptedData");
		debugEncryptedDataService.save(encryptedData, transaction);
	}

	@Override
	public void release(String json) {
		JSONObject jsonObject = new JSONObject(json);
		String transactionId = jsonObject.getString("idTransaction");
		debugComponentService.release(transactionId);
		debugCryptoChannelService.release(transactionId);
		debugEncryptedDataService.release(transactionId);
		debugPageProtectionService.release(transactionId);
		debugVariableMapService.release(transactionId);
	}
	
	private List<String> convertJSONObjectInListString(JSONObject request, String key) {
		List<String> out = new ArrayList<String>();
		JSONArray values = request.getJSONArray(key);
		for (int i = 0; i < values.length(); i++) {
			String value = (String) values.get(i);
			out.add(value);
		}
		return out;
	}

}

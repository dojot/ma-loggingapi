package br.com.dojot.mutualauthentication.loggingapi.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.DebugVariableMapVO;
import br.com.dojot.mutualauthentication.loggingapi.dao.api.DebugVariableMapDAO;
import br.com.dojot.mutualauthentication.loggingapi.service.api.DebugVariableMapService;

@Stateless
public class DebugVariableMapServiceImpl implements DebugVariableMapService {

	@EJB
	private DebugVariableMapDAO debugVariableMapDAO;

	@Override
	public void save(JSONObject json, String transaction) {
		Map<String, Object> returnVariableMap = jsonToMap(json);		
		for (String key : returnVariableMap.keySet()) {
			String data = (String)returnVariableMap.get(key);
			DebugVariableMapVO vo = new DebugVariableMapVO();
			vo.setKey(key);
			vo.setData(data);
			vo.setTransaction(transaction);
			debugVariableMapDAO.save(vo);
		}		
	}
	
	public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
		Map<String, Object> retMap = new HashMap<String, Object>();
		if (json != JSONObject.NULL) {
			retMap = toMap(json);
		}
		return retMap;
	}

	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);
			if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	@Override
	public void release(String transactionId) {
		debugVariableMapDAO.remove(transactionId);
	}

}

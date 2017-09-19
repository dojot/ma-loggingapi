package br.com.cpqd.mutualauthentication.loggingapi.service.impl;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.json.JSONObject;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.DebugCryptoChannelVO;
import br.com.cpqd.mutualauthentication.loggingapi.dao.api.DebugCryptoChannelDAO;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.DebugCryptoChannelService;

@Stateless
public class DebugCryptoChannelServiceImpl implements DebugCryptoChannelService {

	@EJB
	private DebugCryptoChannelDAO debugCryptoChannelDAO;

	@Override
	public void save(String json) {
		JSONObject jsonObject = new JSONObject(json);
		String ivComponentToServer = jsonObject.getString("ivComponentToServer");
		String ivServerToComponent = jsonObject.getString("ivServerToComponent");
		String keyComponentToServer = jsonObject.getString("keyComponentToServer");
		String keyServerToComponent = jsonObject.getString("keyServerToComponent");
		String provider = jsonObject.getString("provider");
		String sessionId = jsonObject.getString("sessionId");
		String transactionId = jsonObject.getString("transactionId");
		Integer tagLen = jsonObject.getInt("tagLen");
		DebugCryptoChannelVO vo = new DebugCryptoChannelVO();
		vo.setDate(new Date());
		vo.setIvComponentToServer(ivComponentToServer);
		vo.setIvServerToComponent(ivServerToComponent);
		vo.setKeyComponentToServer(keyComponentToServer);
		vo.setKeyServerToComponent(keyServerToComponent);
		vo.setProvider(provider);
		vo.setSessionId(sessionId);
		vo.setTagLen(tagLen);
		vo.setTransactionId(transactionId);
		debugCryptoChannelDAO.save(vo);
	}

	@Override
	public void release(String transactionId) {
		debugCryptoChannelDAO.remove(transactionId);
	}

}

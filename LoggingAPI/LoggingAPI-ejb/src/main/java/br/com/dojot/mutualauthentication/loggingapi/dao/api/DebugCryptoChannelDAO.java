package br.com.dojot.mutualauthentication.loggingapi.dao.api;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.DebugCryptoChannelVO;

public interface DebugCryptoChannelDAO {

	void save(DebugCryptoChannelVO vo);
	
	void remove(String transactionId);

}

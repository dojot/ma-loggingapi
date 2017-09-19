package br.com.cpqd.mutualauthentication.loggingapi.dao.api;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.DebugCryptoChannelVO;

public interface DebugCryptoChannelDAO {

	void save(DebugCryptoChannelVO vo);
	
	void remove(String transactionId);

}

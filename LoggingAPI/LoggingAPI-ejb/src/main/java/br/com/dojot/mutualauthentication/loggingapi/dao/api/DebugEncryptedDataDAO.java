package br.com.dojot.mutualauthentication.loggingapi.dao.api;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.DebugEncryptedDataVO;

public interface DebugEncryptedDataDAO {

	void save(DebugEncryptedDataVO vo);
	
	void remove(String id);
}

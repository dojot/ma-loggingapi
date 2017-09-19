package br.com.cpqd.mutualauthentication.loggingapi.dao.api;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.DebugEncryptedDataVO;

public interface DebugEncryptedDataDAO {

	void save(DebugEncryptedDataVO vo);
	
	void remove(String id);
}

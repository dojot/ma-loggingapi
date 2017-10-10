package br.com.dojot.mutualauthentication.loggingapi.dao.api;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.DebugPageProtectionVO;

public interface DebugPageProtectionDAO {

	void save(DebugPageProtectionVO vo);

	void remove(String transactionId);
}

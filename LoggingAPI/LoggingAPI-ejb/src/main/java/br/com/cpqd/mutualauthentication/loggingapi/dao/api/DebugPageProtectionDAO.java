package br.com.cpqd.mutualauthentication.loggingapi.dao.api;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.DebugPageProtectionVO;

public interface DebugPageProtectionDAO {

	void save(DebugPageProtectionVO vo);

	void remove(String transactionId);
}

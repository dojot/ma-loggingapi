package br.com.cpqd.mutualauthentication.loggingapi.dao.api;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.DebugVariableMapVO;

public interface DebugVariableMapDAO {

	void save(DebugVariableMapVO vo);

	void remove(String transactionId);
}

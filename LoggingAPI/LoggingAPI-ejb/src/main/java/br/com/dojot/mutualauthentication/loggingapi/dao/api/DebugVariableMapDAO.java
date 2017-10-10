package br.com.dojot.mutualauthentication.loggingapi.dao.api;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.DebugVariableMapVO;

public interface DebugVariableMapDAO {

	void save(DebugVariableMapVO vo);

	void remove(String transactionId);
}

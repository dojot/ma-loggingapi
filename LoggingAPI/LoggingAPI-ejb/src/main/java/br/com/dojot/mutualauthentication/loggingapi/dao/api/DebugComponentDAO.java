package br.com.dojot.mutualauthentication.loggingapi.dao.api;

import br.com.dojot.mutualauthentication.loggingapi.beans.entity.DebugComponentVO;

public interface DebugComponentDAO {

	void save(DebugComponentVO vo);

	void remove(String transactionId);
}

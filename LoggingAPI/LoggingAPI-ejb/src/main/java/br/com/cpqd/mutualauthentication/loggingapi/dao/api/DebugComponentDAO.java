package br.com.cpqd.mutualauthentication.loggingapi.dao.api;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.DebugComponentVO;

public interface DebugComponentDAO {

	void save(DebugComponentVO vo);

	void remove(String transactionId);
}

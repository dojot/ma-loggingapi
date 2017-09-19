package br.com.cpqd.mutualauthentication.loggingapi.service.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.DebugEncryptedDataVO;
import br.com.cpqd.mutualauthentication.loggingapi.dao.api.DebugEncryptedDataDAO;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.DebugEncryptedDataService;

@Stateless
public class DebugEncryptedDataServiceImpl implements DebugEncryptedDataService {

	@EJB
	private DebugEncryptedDataDAO debugEncryptedDataDAO;

	@Override
	public void save(List<String> encryptedData, String transaction) {
		for (String data : encryptedData) {
			DebugEncryptedDataVO encryptedDataVO = new DebugEncryptedDataVO();
			encryptedDataVO.setEncryptedData(data);
			encryptedDataVO.setTransaction(transaction);
			debugEncryptedDataDAO.save(encryptedDataVO);
		}
	}

	@Override
	public void release(String transactionId) {
		debugEncryptedDataDAO.remove(transactionId);	
	}

}

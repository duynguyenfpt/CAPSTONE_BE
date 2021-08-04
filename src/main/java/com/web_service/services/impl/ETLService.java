package com.web_service.services.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web_service.entity.ETLEntity;
import com.web_service.repository.ETLRequestRepository;
import com.web_service.services.IETLService;

@Service
public class ETLService implements IETLService{
	@Autowired
	ETLRequestRepository etlRequestRepository;

	@Override
	public ETLEntity getResult(Integer requestId) {
//		HdfsService hdfsService = new HdfsService();
		ETLEntity etlEntity = etlRequestRepository.findByRequestId(requestId);
//		try {
//			hdfsService.readFileFromHDFS();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if(etlEntity.getResultStatus()) {
			return etlEntity;
		}
		
		return null;
	}

}

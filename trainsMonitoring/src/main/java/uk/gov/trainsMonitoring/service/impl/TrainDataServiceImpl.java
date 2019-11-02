package uk.gov.trainsMonitoring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.gov.trainMonitoring.model.TrainDelay;
import uk.gov.trainMonitoring.model.TrainDriver;
import uk.gov.trainsMonitoring.dao.TrainDelayDao;
import uk.gov.trainsMonitoring.dao.impl.TrainDelayDaoImpl;
import uk.gov.trainsMonitoring.service.TrainDataService;

@Service
public class TrainDataServiceImpl implements TrainDataService  {
	@Autowired
	TrainDelayDao trainDelayDao;
	
	@Transactional
	public TrainDelay create(TrainDelay trainDelay) {
		
	//	TrainDelayDao td=new TrainDelayDaoImpl();
			
	
		System.out.println("Before calling dao.create");
		return trainDelayDao.create(trainDelay);
	}
	
	
}

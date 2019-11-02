/**
 * 
 */
/**
 * @author bhatiah
 *
 */
package uk.gov.trainsMonitoring.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import uk.gov.trainMonitoring.model.TrainDelay;
import uk.gov.trainsMonitoring.dao.TrainDelayDao;

@Repository
public class TrainDelayDaoImpl implements TrainDelayDao{
	
	@PersistenceContext	
	private EntityManager entityManager;

	@Override
	public TrainDelay create(TrainDelay trainDelay) {
		
		entityManager.persist(trainDelay);
		
		return trainDelay;
	}

	
	
}
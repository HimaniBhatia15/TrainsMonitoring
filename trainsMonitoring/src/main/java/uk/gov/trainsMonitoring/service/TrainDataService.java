/**
 * 
 */
/**
 * @author bhatiah
 *
 */
package uk.gov.trainsMonitoring.service;

import uk.gov.trainMonitoring.model.TrainDelay;

public interface TrainDataService{
	
	public TrainDelay create(TrainDelay trainDelay);
}
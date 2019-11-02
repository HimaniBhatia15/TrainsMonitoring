/**
 * 
 */
/**
 * @author bhatiah
 *
 */
package uk.gov.trainsMonitoring.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uk.gov.trainsMonitoring.service.impl.TrainMonitoringHandler;

@SpringBootApplication
public class TrainsMonitoringApplication{
	
	public static void main(String args[]){
		
		SpringApplication.run(TrainsMonitoringApplication.class, args);
		
		//TrainMonitoringHandler th = new TrainMonitoringHandler();
		
		//th.readFeed("DriverAndDelayDetails.txt");
		
	}
}

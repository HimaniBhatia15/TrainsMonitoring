/**
 * 
 */
/**
 * @author bhatiah
 *
 */
package uk.gov.trainsMonitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.gov.trainsMonitoring.service.impl.TrainMonitoringHandler;

@Controller
@RequestMapping("/getSchedule")
public class TrainsSchedulingController{
	
	private final Logger logger = LoggerFactory.getLogger(TrainsSchedulingController.class);
	
	@Autowired
	private TrainMonitoringHandler trainMonitoringHandler;
	
	@Value( "${train.readFeed.cron-expression}" )
	private String readFeed;
	
	/*Read Feed every 30min and store data in tables*/
	
	
	@Scheduled(cron = "${read.train.feed.cron-expression}")
	@GetMapping("trainFeed")
	public ResponseEntity<String> runDocumentJob() {
		
		logMessage("Starting Reading trains feed");
		
		trainMonitoringHandler.readFeed("DriverAndDelayDetails.txt");
		
		
		return new ResponseEntity<String>("reading and updating feed job finished successfully ", HttpStatus.OK);
		
	}
	

	private void logMessage(String logMessage) {
		if(logger.isDebugEnabled()){
			logger.debug(logMessage);
		}
	
	
	}
}
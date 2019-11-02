package uk.gov.trainsMonitoring.service.impl;

import java.io.File;
import java.text.ParseException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.gov.trainMonitoring.model.TrainDelay;
import uk.gov.trainsMonitoring.service.TrainDataService;
import javax.swing.JOptionPane;

@Component
public class TrainMonitoringHandler {

	private static Logger logger = LoggerFactory.getLogger(TrainMonitoringHandler.class);
	
	
	@Autowired
	private TrainDataService trainDataService;
	private static Scanner x2;
	
	//Feed file will be read every 30min as per cron job
	public void readFeed(String fileName){	
		try{
	
			String trainId="";
			String station="";
			String schdDeparture="";
			String actualDep="";
			String driverName="";
			
			int counter=0;

			x2=new Scanner(new File(fileName));
			
			//Use delimeter to seperate file content
			x2.useDelimiter("\\|");
			
			
			while(x2.hasNext()){
				
				trainId=x2.next();
				station=x2.next();
				schdDeparture=x2.next();
				actualDep=x2.next();
				driverName=x2.next();
				
				
				 System.out.println("trainId:"+trainId+" station:"+station+" schDep:"+schdDeparture+" actDep:"+actualDep
						 +"driverName"+driverName);
				 
				 //As at counter=0, File headers TrainId, Station etc are present
				 if(counter!=0){
				
					 addTrainEntry(trainId,station,schdDeparture,actualDep);
				 }
				 counter++;
			}
			
		}
		catch(Exception ex){
			logger.error("Exception occured while adding train details"+ex);
		}
		
	
	}
	// Create Train entry in database with station, timings and delay
	private void addTrainEntry(String trainId,String station,String schdDeparture,String actualDep) throws ParseException{
		logMessage("Starting making entry in table for train timings and delay");
		System.out.println("In Add train entry");
		
		TrainDelay td =new TrainDelay();
		
		int delay=calculateDelay(schdDeparture,actualDep);
		
		//Setting application data 
		td.setTrainId(trainId);
		td.setStation(station);
		td.setScheduledDepartureTimeAtStation(schdDeparture);
		td.setActualDepartureTimeAtStation(actualDep);
		td.setDepartureLateness(delay);
		
		System.out.println("After setting trainDriver object");
		
		//TrainDataService trainDataService=new TrainDataServiceImpl();
		 trainDataService.create(td);
	
		
		
	}
	
	/*This method calculates difference in train scheduled and actual departure timings
	 * to calculate delay, It can be positive and negative*/
	
	private int calculateDelay(String schdDeparture,String actualDep ) throws ParseException{
		//Calculating delay/lateness in departure
		System.out.println("Before splitting T"+schdDeparture);
		
		String[] dateTimeSplitDep = schdDeparture.split("T");
		
		String depTime=dateTimeSplitDep[1];
		
		String[] timeSplitDep=depTime.split(":");
		
		System.out.println("after splitting T"+schdDeparture);
		
		//For input string: "2018-09-0414"
				
				int hourDep=Integer.parseInt(timeSplitDep[0]);
				int minDep = Integer.parseInt(timeSplitDep[1]);
				int secDep=Integer.parseInt(timeSplitDep[2]);
				
				System.out.println("After sch dep time split,hour:"+hourDep+"min:"+minDep+" sec:"+secDep);
				
				int totalSecDep=(hourDep*60*60)+(minDep*60)+secDep;
				
				System.out.println("Total time in sec:"+totalSecDep);
				
				String[] dateTimeSplitAct = actualDep.split("T");
				
				String actTime=dateTimeSplitAct[1];
				
				String[] timeSplitAct=actTime.split(":");
				
				
				int hourAct=Integer.parseInt(timeSplitAct[0]);
				int minAct= Integer.parseInt(timeSplitAct[1]);
				int secAct=Integer.parseInt(timeSplitAct[2]);
				
				System.out.println("After sch dep time split,hour:"+hourAct+" min:"+minAct+" sec:"+secAct);
				
				
				int totalSecAct=(hourAct*60*60)+(minAct*60)+secAct;
				
				int delay=totalSecAct-totalSecDep;
				System.out.println("Delay:"+delay);
				
				return delay;
				
				
	}
	
	private void logMessage(String logMessage) {
		if(logger.isDebugEnabled()){
			logger.debug(logMessage);
		}
	}
}

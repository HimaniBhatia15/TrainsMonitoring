/**
 * 
 */
/**
 * @author bhatiah
 *
 */
package uk.rail.serviceImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.rail.service.TrainSpeedReader;

public class ServiceImpl implements TrainSpeedReader{
	private static Logger logger=LoggerFactory.getLogger(ServiceImpl.class.getName());
	
	private static String noDetails= "No Details Found";

	@Override
	public void findTrainDetails(String trainName,String fileName)  {
		 int matchedRecords=0;	
		//
    	 try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

    		   String strCurrentLine;
    		
    		   while ((strCurrentLine = br.readLine()) != null) {
    		   
    		    String [] trainNameArr=strCurrentLine.split("-");
    		    
    		    if(trainName.equalsIgnoreCase(trainNameArr[0])){
    		    	matchedRecords++;
    		    }
    		   }
    		   System.out.println("matchedRecords size:"+matchedRecords);
    		   
    		   if(matchedRecords>0){
    			   filterData(fileName,trainName,matchedRecords);
    		   }
    		   else{
    			   logger.info(noDetails);
    			   System.out.println(noDetails);
     			  
    			   JOptionPane.showMessageDialog(null, 
    					   noDetails, 
                           "Train Information", 
                           JOptionPane.WARNING_MESSAGE
    					   );
    			 
    		   }
    	 }
    	 
    	 catch (IOException e) {
    			logger.error("Error Occured while reading feed and filtering data : " , e);
    		}
    	    	 catch(Exception e){
    	    		 logger.error("Unexpected Error Occured while reading feed and filtering data : " , e);
    	    	 }
    	    		  
    	    	
	}
	
    	 
    	 private void filterData(String fileName,String trainName, int numRecords){
    	 
    	String speed=null;
    	String energy=null;
         String trainNameFile=null;
         int []trainSpeed;
         int[] trainEnergy;
    		 
    	trainSpeed=new int[numRecords];
    	 trainEnergy =new int[numRecords];
    	 
    	 int i=0;
    		
    	 try (BufferedReader br1 = new BufferedReader(new FileReader(fileName))) {

  		   String strCurrentLine1;
  		
  		   while ((strCurrentLine1 = br1.readLine()) != null) {
  		   
    		    
    		   String fileData[]= strCurrentLine1.split("-");
    		   
    		    trainNameFile=fileData[0];
    		    speed=fileData[1];
    		    energy=fileData[2];
    		    
    		   
    		    
    		    if(trainName.equalsIgnoreCase(trainNameFile)){
    		    	
    			    	trainSpeed[i]=Integer.parseInt(speed);
    			    	
    			    	trainEnergy[i]=Integer.parseInt(energy);
    			    	
    			    	
    			    	 i++;
    			    	
    		    	}
    		
    		   }
    		   
    
    		   System.out.println("Min Speed:"+sortTrainSpeed(trainSpeed)+" Max Energy:"+sortTrainEnergy(trainEnergy));
    		   
    		   
	} catch (IOException e) {
		logger.error("Error Occured while reading feed and filtering data : " , e);
	}
    	 catch(Exception e){
    		 logger.error("Unexpected Error Occured while reading feed and filtering data : " , e);
    	 }
    		  
	
}
    	 //Sort Train Speed array and display lowest speed
	private static int sortTrainSpeed(int [] trainSpeed){
		System.out.println("Before sort"+trainSpeed[0]+" "+trainSpeed[1]+ " "+trainSpeed[2]);
		
		Arrays.sort(trainSpeed);
		for (int i = 0; i < trainSpeed.length; i++) {
		       System.out.println("sorted array"+trainSpeed[i]);
		    }
		
		return trainSpeed[0];	
	
	}
	
	 //Sort Train Energy array and display max energy
	private static int sortTrainEnergy(int [] trainEnergy){
		
		Arrays.sort(trainEnergy);
		
		return trainEnergy[trainEnergy.length-1];	
	
	}
	
	
	
}
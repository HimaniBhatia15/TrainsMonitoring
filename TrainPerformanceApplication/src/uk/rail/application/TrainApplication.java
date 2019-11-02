/**
 * 
 */
/**
 * @author bhatiah
 *
 */
package uk.rail.application;

import java.util.Scanner;

import uk.rail.serviceImpl.ServiceImpl;

public class TrainApplication{
	public static void main(String args[]){
		ServiceImpl impl = new ServiceImpl();
		try(Scanner sc = new Scanner(System.in)){
			System.out.println("Enter Train name-");
			String trainType =	sc.nextLine();
			
			impl.findTrainDetails(trainType,"TrainDetails.txt");
		}
		

		}
		
}
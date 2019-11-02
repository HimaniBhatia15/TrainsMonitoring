/**
 * 
 */
/**
 * @author bhatiah
 *
 */
package uk.gov.trainMonitoring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="train_driver_details")
public class TrainDriver implements Serializable{

@Id
@Column(name="Journey_id")
private int journeyId;

@Column(name="train_id")
private String trainId;

@Column(name="start_station")
private String startStation;

@Column(name="end_station")
private String endStation;

@Column(name="driver_name")
private String driverName;

@Column(name="journey_status")
private String journeyStatus;

public int getJourneyId() {
	return journeyId;
}

public void setJourneyId(int journeyId) {
	this.journeyId = journeyId;
}

public String getTrainId() {
	return trainId;
}

public void setTrainId(String trainId) {
	this.trainId = trainId;
}

public String getStartStation() {
	return startStation;
}

public void setStartStation(String startStation) {
	this.startStation = startStation;
}

public String getEndStation() {
	return endStation;
}

public void setEndStation(String endStation) {
	this.endStation = endStation;
}

public String getDriverName() {
	return driverName;
}

public void setDriverName(String driverName) {
	this.driverName = driverName;
}

public String getJourneyStatus() {
	return journeyStatus;
}

public void setJourneyStatus(String journeyStatus) {
	this.journeyStatus = journeyStatus;
}





}

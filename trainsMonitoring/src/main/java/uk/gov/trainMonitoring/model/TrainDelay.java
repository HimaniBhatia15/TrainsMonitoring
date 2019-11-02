package uk.gov.trainMonitoring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="train_delay_details")
public class TrainDelay implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Journey_id")
	@GeneratedValue
	private int journeyId;
	
	@Column(name="train_id")
	private String trainId;
	
	@Column(name="station")
	private String station;
	
	@Column(name="Scheduled_Departure_Time_at_station")
	private String scheduledDepartureTimeAtStation;
	
	@Column(name="actual_departure_time_at_station")
	private String actualDepartureTimeAtStation;
	
	@Column(name="departure_lateness_in_seconds")
	private int departureLateness;

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

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getScheduledDepartureTimeAtStation() {
		return scheduledDepartureTimeAtStation;
	}

	public void setScheduledDepartureTimeAtStation(
			String scheduledDepartureTimeAtStation) {
		this.scheduledDepartureTimeAtStation = scheduledDepartureTimeAtStation;
	}

	public String getActualDepartureTimeAtStation() {
		return actualDepartureTimeAtStation;
	}

	public void setActualDepartureTimeAtStation(String actualDepartureTimeAtStation) {
		this.actualDepartureTimeAtStation = actualDepartureTimeAtStation;
	}

	public int getDepartureLateness() {
		return departureLateness;
	}

	public void setDepartureLateness(int departureLateness) {
		this.departureLateness = departureLateness;
	}
	
	
	
	
}

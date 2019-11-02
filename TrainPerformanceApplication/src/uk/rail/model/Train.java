/**
 * 
 */
/**
 * @author bhatiah
 *
 */
package uk.rail.model;

public class Train{

private String type;
private String speed;
private String energy;

public Train(String type, String speed, String energy){
	this.type=type;
	this.speed=speed;
	this.energy=energy;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getSpeed() {
	return speed;
}
public void setSpeed(String speed) {
	this.speed = speed;
}
public String getEnergy() {
	return energy;
}
public void setEnergy(String energy) {
	this.energy = energy;
}
@Override
public String toString(){
	return ("Train type"+type+"Speed"+speed+"Energy"+energy);
}


}